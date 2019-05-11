package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.*;
import by.artezio.cloud.publishing.service.*;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

/**
 * Класс IssueWebFacade в котором размещена основная логика работы.
 *
 * @author Igor Kuzmin
 *
 */
@Component
public class IssueWebFacade {

    private final IssueService issueService;
    private final PublishingService publishingService;
    private final ArticleService articleService;
    private final SecurityService securityService;
    private final ReviewService reviewService;
    private EmployeeService employeeService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * @param articleService - {@link ArticleService}
     * @param reviewService - {@link ReviewService}
     * @param employeeService - {@link EmployeeService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService,
                          final ArticleService articleService,
                          final ReviewService reviewService,
                          final EmployeeService employeeService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
        this.articleService = articleService;
        this.reviewService = reviewService;
        this.employeeService = employeeService;
    }

    /**
     * Проверка статьи на допуск в публикацию.
     * @param article - {@link by.artezio.cloud.publishing.domain.Article}
     * @return - флаг допуска статьи.
     * */
    private boolean articleIsNotApproved(final ArticleDto article) {
        List<Review> reviews =
            reviewService.getReviewsByArticleId(article.getId());
        for (Review r : reviews) {
            if (!r.isApproved()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Метод для получения списка {@link IssueInfo} который содержит информацию
     * о номерах доступные текущему пользователю.
     *
     * @return список {@link IssueInfo}
     *
     */
    public List<IssueInfo> getIssueInfoList() {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        List<IssueInfo> issueInfoList = new ArrayList<>();
        List<PublishingDTO> publishingList;
        if (user.isChiefEditor()) {
            publishingList = publishingService.getPublishingList();
        } else {
            publishingList
                    = publishingService.getPublishingListByEmployeeId(user.getId());
        }
        for (PublishingDTO p : publishingList) {
            List<IssueInfo> listForPublishing
                    = issueService.getIssueListByPublishingId(p.getId());
            for (IssueInfo issueInfo : listForPublishing) {
                issueInfo.setPublishingTitle(p.getTitle());
                int numberOfArticles
                        = issueService.getArticleIdList(issueInfo.getIssueId()).size();
                issueInfo.setNumberOfArticle(numberOfArticles);
                issueInfoList.add(issueInfo);
            }
        }
        return issueInfoList;
    }

    /**
     * Метод для получения списка который содержит информацию о журналах/газетах
     * {@link PublishingDTO} доступные текущему пользователю. Предназначена лля
     * выпадающего списка на форме добавления/редактирования номеров.
     *
     * @return список {@link PublishingDTO}
     *
     */
    public List<PublishingDTO> getPublishingList() {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
            return publishingService.getPublishingListByEmployeeId(user.getId());
        }
        return publishingService.getPublishingList();
    }

    /**
     * Метод для получения dto {@link IssueForm} по id
     * {@link by.artezio.cloud.publishing.domain.Issue}.
     *
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return {@link IssueForm}
     *
     */
    public IssueView getIssueViewByIssueId(final int issueId) {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        IssueView issueView = issueService.getIssueViewByIssueId(issueId);
        List<Integer> articleIdList = issueService.getArticleIdList(issueId);
        List<ArticleDto> articles = new ArrayList<>();
        for (Integer id : articleIdList) {
            articles.add(articleService.getArticleDtoById(id));
        }
        issueView.setArticles(articles);
        PublishingDTO publishing =
            publishingService.getPublishingById(issueView.getPublishingId());
        issueView.setPublishingTitle(publishing.getTitle());
        return issueView;
    }

    /**
     * Получение объекта dto {@link IssueForm} по id {@link Issue}.
     * В данном случаи {@link IssueForm} несет информацию для заполнения формы
     * данными о {@link Issue}.
     * @param issueId - id {@link Issue}.
     * @return - {@link IssueForm}.
     * */
    public IssueForm getIssueFormByIssueId(final int issueId) {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        IssueForm issueForm = issueService.getIssueFormByIssueId(issueId);
        issueForm.setArticlesId(issueService.getArticleIdList(issueId));
        return issueForm;
    }

    /**
     * Метод для получения статей по списку id статей.
     * @param articlesIdList - список id статей.
     * @return - {@link ArticleDto}.
     * */
    public List<ArticleDto> getArticlesByArticlesIdList(final List<Integer> articlesIdList) {
        List<ArticleDto> articles = new ArrayList<>();
        if (articlesIdList != null) {
            for (Integer id : articlesIdList) {
                articles.add(articleService.getArticleDtoById(id));
            }
        }
        return articles;
    }

    /**
     * Метод для получения списка который содержит
     * информацию о тематиках {@link by.artezio.cloud.publishing.domain.Topic}
     * для данного {@link PublishingDTO}. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @param publishingId - id {@link PublishingDTO}
     * @return список {@link by.artezio.cloud.publishing.domain.Topic}
     *
     */
    public List<TopicShortInfo> getTopicListByPublishingId(final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * Получение списка допущенных в публикацию авторов.
     * @param authorFilter - {@link AuthorFilter}.
     * @return список {@link Employee}.
     * */
    public List<Employee> getApprovedAuthors(final AuthorFilter authorFilter) {
        List<Integer> authorsIdList = articleService.getAuthorsIdList(authorFilter);
        ArticleFilter articleFilter = new ArticleFilter();
        articleFilter.setPublishingId(authorFilter.getPublishingId());
        articleFilter.setTopicId(authorFilter.getTopicId());
        authorsIdList.removeIf(authorId -> {
            articleFilter.setAuthorId(authorId);
            return getApprovedArticles(articleFilter).isEmpty();
        });
        List<Employee> authors = new ArrayList<>();
        for (Integer id : authorsIdList) {
            authors.add(employeeService.getEmployeeById(id));
        }
        return authors;
    }

    /**
     * Получение списка допущенных в публикацию статей {@link by.artezio.cloud.publishing.domain.Article}.
     * @param articleFilter - {@link ArticleFilter}.
     * @return список {@link by.artezio.cloud.publishing.domain.Article}.
     * */
    public List<ArticleDto> getApprovedArticles(final ArticleFilter articleFilter) {
        List<ArticleDto> articles = articleService.getArticleDtoList(articleFilter);
        articles.removeIf(this::articleIsNotApproved);
        return articles;
    }

    /**
     * Удаление номра по id, а также всех статей номера и рекламы.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @return - {@link IssueOperationResult}.
     * */
    public IssueOperationResult deleteIssueById(final int issueId) {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        Issue issue = issueService.deleteIssueById(issueId);
        IssueOperationResult result = new IssueOperationResult();
        result.setNumber(issue.getNumber());
        Integer publishingId = issue.getPublishingId();
        result.setPublishingTitle(publishingService.getPublishingTitle(publishingId));
        result.setStatus("deleted");
        return result;
    }

    /**
     * Метод для создания нового номера и сохранеия его в бд.
     *
     * @param issueForm - {@link IssueForm}.
     * @return - {@link IssueOperationResult}.
     * */
    public IssueOperationResult createNewIssue(final IssueForm issueForm) {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        Integer issueId = issueService.createNewIssue(issueForm);
        IssueOperationResult result = new IssueOperationResult();
        PublishingDTO publishingDTO = publishingService.getPublishingById(issueForm.getPublishingId());
        result.setPublishingTitle(publishingDTO.getTitle());
        result.setNumber(issueForm.getNumber());
        result.setIssueId(issueId);
        result.setStatus("created");
        return result;
    }

    /**
     * Метод для обновления и сохранения в бд информации по уже существующему
     * номеру.
     *
     * @param issueForm - {@link IssueForm}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @return - {@link IssueOperationResult}.
     * */
    public IssueOperationResult updateIssue(final Integer issueId, final IssueForm issueForm) {
        User user = securityService.getCurrentUser();
        if (!user.isChiefEditor()) {
            securityService.checkIsEditor();
        }
        issueService.updateIssue(issueId, issueForm);
        PublishingDTO publishingDTO = publishingService.getPublishingById(issueForm.getPublishingId());
        IssueOperationResult result = new IssueOperationResult();
        result.setNumber(issueForm.getNumber());
        result.setPublishingTitle(publishingDTO.getTitle());
        result.setIssueId(issueId);
        result.setStatus("updated");
        return result;
    }
}
