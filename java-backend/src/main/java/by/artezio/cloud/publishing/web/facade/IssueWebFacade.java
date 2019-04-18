package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.ArticleService;
import by.artezio.cloud.publishing.service.AdvertisingService;
import by.artezio.cloud.publishing.service.ReviewService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;


/**
 * Класс IssueWebFacade в котором размещена основная логика работы.
 * @author Igor Kuzmin
 * */
@Component
public class IssueWebFacade {

    private IssueService issueService;

    private PublishingService publishingService;

    private ArticleService articleService;

    private SecurityService securityService;

    private AdvertisingService advertisingService;

    private ReviewService reviewService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * @param articleService - {@link AdvertisingService}
     * @param advertisingService - {@link AdvertisingService}
     * @param reviewService - {@link ReviewService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService,
                          final ArticleService articleService,
                          final AdvertisingService advertisingService,
                          final ReviewService reviewService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
        this.articleService = articleService;
        this.advertisingService = advertisingService;
        this.reviewService = reviewService;
    }

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueForm}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueForm}
     * */
    private IssueForm mapIssueToIssueForm(final Issue issue) {
        IssueForm issueForm = new IssueForm();
        PublishingDTO publishing = publishingService
            .getPublishingById(issue.getId());
        issueForm.setPublishingId(publishing.getId());
        issueForm.setPublishingTitle(publishing.getTitle());
        List<Integer> articleIdList = issueService.getArticleIdList(issue.getId());
        issueForm.setArticlesId(articleIdList);
        List<Advertising> advertising =
            advertisingService.getAdvertising(issue.getId());
        List<String> advertisingPath = new ArrayList<>();
        for (Advertising a : advertising) {
            advertisingPath.add(a.getFilePath());
        }
        issueForm.setAdvertisingPath(advertisingPath);
        issueForm.setNumber(issue.getNumber());
        issueForm.setLocalDate(issue.getDate());
        issueForm.setPublished(issue.isPublished());
        issueForm.setIssueId(issue.getId());
        return issueForm;
    }

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueInfo}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueInfo}
     * */
    private IssueInfo mapIssueToIssueInfo(final Issue issue) {
        IssueInfo issueInfo = new IssueInfo();
        PublishingDTO publishing =
            publishingService.getPublishingById(issue.getPublishingId());
        issueInfo.setPublishingTitle(publishing.getTitle());
        List<Integer> articleIdList =
            issueService.getArticleIdList(issue.getId());
        issueInfo.setNumberOfArticle(articleIdList.size());
        issueInfo.setPublished(issue.isPublished());
        issueInfo.setNumber(issue.getNumber());
        issueInfo.setLocalDate(issue.getDate());
        issueInfo.setIssueId(issue.getId());
        return issueInfo;
    }

    /**
     * Функция преобразования списка {@link Issue}
     * в список {@link IssueForm}.
     * @param issueList список {@link Issue}
     * @return список {@link IssueForm}
     * */
    private List<IssueInfo> mapIssueListToIssueInfoList(final List<Issue> issueList) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issueList) {
            IssueInfo issueInfo = mapIssueToIssueInfo(issue);
            issueInfoList.add(issueInfo);
        }
        return issueInfoList;
    }

    /**
     * Проверка статьи на допуск в публикацию.
     * @param article - {@link Article}
     * @return - флаг допуска статьи.
     * */
    private boolean articleIsApproved(final Article article) {
        List<Review> reviews =
            reviewService.getReviewsByArticleId(article.getId());
        if (reviews.size() == 0) {
            return false;
        }
        for (Review r : reviews) {
            if (!r.isApproved()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка содержит ли автор статьи допущенные в публикакию.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link Topic}.
     * @param authorId - id {@link by.artezio.cloud.publishing.domain.Employee}.
     * */
    private boolean journalistIsApproved(final int publishingId,
                                     final int topicId,
                                     final int authorId) {
        List<Article> articles =
            articleService.getArticlesBytopicAndPublishingAndAuthorId(topicId,
            publishingId, authorId);
        for (Article a : articles) {
            if (articleIsApproved(a)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для получения списка {@link IssueInfo} который содержит
     * информацию о номерах доступные текущему пользователю.
     * @return список {@link IssueInfo}
     * */
    public List<IssueInfo> getIssueInfoList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        List<Issue> issues = new ArrayList<>();
        if (user.isChiefEditor()) {
            issues = issueService.getListOfAllIssues();
        } else {
            List<PublishingDTO> publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
            for (PublishingDTO p : publishingList) {
                List<Issue> publishingIssues =
                    issueService.getIssueListByPublishingId(p.getId());
                issues.addAll(publishingIssues);
            }
        }
        return mapIssueListToIssueInfoList(issues);
    }

    /**
     * Метод для получения списка который содержит
     * информацию о журналах/газетах {@link PublishingDTO}
     * доступные текущему пользователю. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @return список {@link PublishingDTO}
     * */
    public List<PublishingDTO> getPublishingList() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        if (user.isChiefEditor()) {
            return publishingService.getPublishingList();
        }
        return publishingService.getPublishingListByEmployeeId(user.getId());
    }

    /**
     * Метод для получения dto {@link IssueForm}
     * по id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return {@link IssueForm}
     * */
    public IssueForm getIssueFormByIssueId(final int issueId) {
        securityService.checkIsEditor();
        Issue issue = issueService.getIssueById(issueId);
        return mapIssueToIssueForm(issue);
    }


    /**
     * Метод для получения списка который содержит
     * информацию о тематиках {@link Topic}
     * для данного {@link PublishingDTO}. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @param publishingId - id {@link PublishingDTO}
     * @return список {@link Topic}
     * */
    public List<Topic> getTopicListByPublishingId(final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * Получение списка допущенных в публикацию авторов.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link Topic}.
     * @return список {@link Employee}.
     * */
    public List<Employee> getApprovedJournalist(final int publishingId,
                                            final int topicId) {
        List<Employee> journalists =
            publishingService.getPublishingJournalist(publishingId);
        List<Employee> approvedJournalist = new ArrayList<>();
        for (Employee j : journalists) {
            if (journalistIsApproved(publishingId, topicId, j.getId())) {
                approvedJournalist.add(j);
            }
        }
        return approvedJournalist;
    }

    /**
     * Получение списка допущенных в публикацию статей {@link Article}.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link Topic}.
     * @param authorId - id {@link Employee}
     * @return список {@link Article}.
     * */
    public List<Article> getApprovedArticles(final int publishingId,
                                             final int topicId,
                                             final int authorId) {
        List<Article> articles =
            articleService.getArticlesBytopicAndPublishingAndAuthorId(topicId,
                publishingId, authorId);
        List<Article> approvedArticles = new ArrayList<>();
        for (Article a : articles) {
            if (articleIsApproved(a)) {
                approvedArticles.add(a);
            }
        }
        return approvedArticles;
    }

    /**
     * Удаление номра по id, а также всех статей номера и рекламы.
     * @param issueId - id {@link Issue}
     * */
    public void deleteIssueById(final int issueId) {
        advertisingService.deleteAdvertisingByIssueId(issueId);
        issueService.deleteIssueById(issueId);
    }

    /**
     * Получение списка статей по списку id.
     * @param articleIdList - список id статей.
     * @return список {@link Article}.
     * */
    public List<Article> getArticlesForIssue(final List<Integer> articleIdList) {
        List<Article> articles = new ArrayList<>();
        for (Integer id : articleIdList) {
            articles.add(articleService.getArticleById(id));
        }
        return articles;
    }

}
