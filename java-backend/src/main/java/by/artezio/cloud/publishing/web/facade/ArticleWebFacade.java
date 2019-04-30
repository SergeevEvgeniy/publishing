package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.ArticleView;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.ReviewService;
import by.artezio.cloud.publishing.service.TopicService;
import by.artezio.cloud.publishing.service.converter.EmployeeToEmployeeShortInfoConverter;
import by.artezio.cloud.publishing.service.converter.TopicToTopicShortInfoConverter;
import by.artezio.cloud.publishing.web.security.AccessDeniedException;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Denis Shubin
 */
@Component
public class ArticleWebFacade {

    private SecurityService securityService;
    private ArticleService articleService;
    private EmployeeService employeeService;
    private PublishingService publishingService;
    private TopicService topicService;
    private ReviewService reviewService;
    private TopicToTopicShortInfoConverter topicConverter;
    private EmployeeToEmployeeShortInfoConverter employeeConverter;

    /**
     * @param securityService   {@link SecurityService}
     * @param articleService    {@link ArticleService}
     * @param employeeService   {@link EmployeeService}
     * @param publishingService {@link PublishingService}
     * @param topicService      {@link TopicService}
     * @param reviewService     {@link ReviewService}
     * @param topicConverter    {@link TopicToTopicShortInfoConverter}
     * @param employeeConverter {@link EmployeeToEmployeeShortInfoConverter}
     */
    public ArticleWebFacade(final SecurityService securityService,
                            final ArticleService articleService,
                            final EmployeeService employeeService,
                            final PublishingService publishingService,
                            final TopicService topicService,
                            final ReviewService reviewService,
                            final TopicToTopicShortInfoConverter topicConverter,
                            final EmployeeToEmployeeShortInfoConverter employeeConverter) {
        this.securityService = securityService;
        this.articleService = articleService;
        this.employeeService = employeeService;
        this.publishingService = publishingService;
        this.topicService = topicService;
        this.reviewService = reviewService;
        this.topicConverter = topicConverter;
        this.employeeConverter = employeeConverter;
    }

    /**
     * @return {@link List} of {@link ArticleInfo}
     */
    public List<ArticleInfo> getArticleInfoList() {
        User current = securityService.getCurrentUser();

        List<ArticleInfo> list = articleService.getArticleInfoList(current);
        for (ArticleInfo info : list) {
            ArticleDto article = articleService.getArticleDto(info.getArticleId());
            info.setPublishing(
                publishingService.getPublishingTitle(
                    article.getPublishingId()
                )
            );
            info.setTopic(
                topicService.getTopicName(
                    article.getTopicId()
                )
            );
            info.setAuthorFullName(
                employeeService.getShortEmployee(
                    article.getAuthorId()
                ).getShortFullName()
            );

            List<ArticleCoauthor> coauthors = articleService.getCoauthorsByArticleId(article.getId());
            List<String> coauthorNames = new ArrayList<>(coauthors.size());
            for (ArticleCoauthor coauthor : coauthors) {
                coauthorNames.add(employeeService.getShortEmployee(
                    coauthor.getEmployeeId()
                ).getShortFullName());
            }
            info.setCoauthors(coauthorNames);

            info.setReviewed(reviewService.getReviewsByArticleId(article.getId()).size() != 0);
        }
        return list;
    }

    /**
     * @param articleId id статьи
     * @return {@link ArticleForm}
     */
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        User currentUser = securityService.getCurrentUser();
        ArticleDto article = articleService.getArticleDtoById(articleId);
        if (article.getAuthorId() != currentUser.getId()) {
            throw new AccessDeniedException("Current user is not an author.");
        }

        ArticleForm articleForm = articleService.getUpdateArticleFormByArticleId(articleId);
        articleForm.setShortInfos(getReviewShortInfos(articleId));
        return articleForm;
    }


    /**
     * @param publishingId id журнала/газеты
     * @return {@link List} of {@link Topic}
     */
    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return topicService.getTopicsByPublishingId(publishingId);
    }

    /**
     * Удаление статьи по её идентификатору.
     *
     * @param articleId id статьи
     */
    public void deleteArticleById(final int articleId) {
        articleService.deleteArticle(articleId);
    }

    /**
     * @param articleId id статьи
     * @return {@link ArticleView}
     */
    public ArticleView getArticleViewById(final int articleId) {
        ArticleView view = new ArticleView();
        ArticleDto article = articleService.getArticleDtoById(articleId);
        Topic topic = topicService.getTopicById(article.getTopicId());
        PublishingDTO publishing = publishingService.getPublishingById(article.getPublishingId());
        List<ArticleCoauthor> articleCoauthors = articleService.getCoauthorsByArticleId(articleId);
        List<Employee> coauthors = new ArrayList<>(articleCoauthors.size());
        for (ArticleCoauthor ac : articleCoauthors) {
            coauthors.add(employeeService.getEmployeeById(ac.getEmployeeId()));
        }
        List<Review> reviewList = reviewService.getReviewsByArticleId(articleId);
        Map<Employee, Review> reviews = new HashMap<>(reviewList.size());
        for (Review r : reviewList) {
            reviews.put(employeeService.getEmployeeById(r.getReviewerId()), r);
        }

        view.setArticleId(articleId);
        view.setPublishingName(publishing.getTitle());
        view.setTopicName(topic.getName());
        view.setArticleName(article.getTitle());
        view.setContent(article.getContent());
        view.setCoauthors(coauthors);
        view.setReviews(reviews);
        return view;
    }

    /**
     * @param articleId  id статьи
     * @param reviewerId id рецензента
     * @return рецензия {@link Review}
     */
    public ReviewShortInfo getReviewShortInfo(final int articleId, final int reviewerId) {
        return reviewService.getReviewShortInfo(articleId, reviewerId);
    }

    /**
     * @return {@link List} of {@link PublishingDTO}
     */
    public List<PublishingDTO> getPublishingDtoList() {
        return publishingService.getPublishingList();
    }

    /**
     * @param publishingId id журнала
     * @return список {@link TopicShortInfo}
     */
    public List<TopicShortInfo> getTopicsShortInfoList(final Integer publishingId) {
        List<Topic> topics = topicService.getTopicsByPublishingId(publishingId);
        List<TopicShortInfo> list = new ArrayList<>(topics.size());
        for (Topic t : topics) {
            TopicShortInfo shortInfo = topicConverter.convert(t);
            list.add(shortInfo);
        }
        return list;
    }

    /**
     * @param publishingId id журнала
     * @return список {@link EmployeeShortInfo}
     */
    public List<EmployeeShortInfo> getAvailableCoauthors(final Integer publishingId) {
        User current = securityService.getCurrentUser();
        return employeeService.getEmployeesByPublishingId(publishingId)
            .stream()
            .filter(employee -> employee.getId() != current.getId())
            .filter(info -> info.getType() == 'J')
            .map(employeeConverter::convert)
            .collect(Collectors.toList());
    }

    /**
     * @param articleId id статьи
     * @return список {@link EmployeeShortInfo}
     */
    public List<EmployeeShortInfo> getCurrentCoauthors(final int articleId) {
        List<ArticleCoauthor> articleCoauthors = articleService.getCoauthorsByArticleId(articleId);
        List<EmployeeShortInfo> employees = new ArrayList<>(articleCoauthors.size());
        for (ArticleCoauthor ac : articleCoauthors) {
            EmployeeShortInfo shortEmployee = employeeService.getShortEmployee(ac.getEmployeeId());
            employees.add(shortEmployee);
        }
        return employees;
    }

    /**
     * @param articleId id статьи
     * @return список {@link ReviewShortInfo}
     */
    public List<ReviewShortInfo> getReviewShortInfos(final int articleId) {
        List<ReviewShortInfo> reviewShortInfoList = reviewService.getReviewShortInfoList(articleId);
        for (ReviewShortInfo shortInfo : reviewShortInfoList) {
            shortInfo.setReviewerShortName(
                employeeService.getShortEmployee(shortInfo.getReviewerId()).getShortFullName()
            );
        }
        return reviewShortInfoList;
    }

    /**
     * @param employeeId id сотрудника
     * @return {@link EmployeeShortInfo}
     */
    public EmployeeShortInfo getShortEmployeeById(final int employeeId) {
        return employeeConverter.convert(
            employeeService.getEmployeeById(employeeId)
        );
    }

    /**
     * @param articleForm {@link ArticleForm}
     */
    public void save(final ArticleForm articleForm) {
        articleForm.setAuthorId(securityService.getCurrentUser().getId());
        articleService.save(articleForm);
    }

    /**
     * @param articleForm {@link ArticleForm}
     * @param articleId   id статьи
     */
    public void update(final ArticleForm articleForm, final Integer articleId) {
        articleService.update(articleForm, articleId);
    }

    /**
     * Проверка, прошёл ли пользователь аутентификацию. Если нет, бросится исключение {@link AccessDeniedException}.
     */
    public void isAuthorized() {
        if (securityService.getCurrentUser() == null) {
            throw new AccessDeniedException("User is not authorized");
        }
    }

    /**
     * @return {@code true}, если текущий пользователь является главным редактором
     */
    public boolean isChiefEditor() {
        return securityService.getCurrentUser().isChiefEditor();
    }

    /**
     * @return {@code true}, если текущий пользователь является журналистом
     */
    public boolean isJournalist() {

        return securityService
            .getCurrentUser()
            .getType()
            .equals('J');
    }

    /**
     * Проверка, существует ли статья с таким id.
     *
     * @param articleId id статьи
     * @return {@code true}, если статья существует, иначе - {@code false}
     */
    public boolean isArticleExists(final int articleId) {
        return articleService.isArticleExists(articleId);
    }
}
