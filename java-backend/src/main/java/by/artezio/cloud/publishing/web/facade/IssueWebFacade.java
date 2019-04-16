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
import java.util.Map;
import java.util.HashMap;

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
    private boolean authorIsApproved(final int publishingId,
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
     * Метод для получения  {@link Map} которая содержит
     * информацию о журналах/газетах {@link PublishingDTO}
     * доступные текущему пользователю. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @return {@link Map}, где ключом является
     * id {@link PublishingDTO} значением является название {@link PublishingDTO}
     * */
    public Map<Integer, String> getPublishingMap() {
        securityService.checkIsEditor();
        User user = securityService.getCurrentUser();
        Map<Integer, String> publishingMap = new HashMap<>();
        List<PublishingDTO> publishingList;
        if (user.isChiefEditor()) {
            publishingList = publishingService.getPublishingList();
        } else {
            publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
        }
        for (PublishingDTO p : publishingList) {
            publishingMap.put(p.getId(), p.getTitle());
        }
        return publishingMap;
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
     * Метод для получения  {@link Map} которая содержит
     * информацию о тематиках {@link Topic}
     * для данного {@link PublishingDTO}. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @param publishingId - id {@link PublishingDTO}
     * @return {@link Map}, где ключом является
     * id {@link Topic} значением является название {@link Topic}
     * */
    public Map<Integer, String> getTopicMapByPublishingId(final int publishingId) {
        List<Topic> topicList =
            publishingService.getTopicsByPublishingId(publishingId);
        Map<Integer, String> topicMap = new HashMap<>();
        for (Topic topic : topicList) {
            topicMap.put(topic.getId(), topic.getName());
        }
        return topicMap;
    }

    /**
     * Получение {@link Map} допущенных в публикацию авторов.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link Topic}.
     * @return {@link Map}, где ключом является
     * id {@link Employee} значением является имя {@link Employee}
     * */
    public Map<Integer, String> getApprovedAuthor(final int publishingId,
                                                  final int topicId) {
        List<Employee> journalists =
            publishingService.getPublishingJournalist(publishingId);
        Map<Integer, String> authorMap = new HashMap<>();
        for (Employee e : journalists) {
            if (authorIsApproved(publishingId, topicId, e.getId())) {
                authorMap.put(e.getId(), e.getLastName());
            }
        }
        return authorMap;
    }

    /**
     * Получение {@link Map} допущенных в публикацию статей.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link Topic}.
     * @param authorId - id {@link Employee}
     * @return {@link Map}, где ключом является
     * id {@link Article} значением является название {@link Article}
     * */
    public Map<Integer, String> getApprovedArticles(final int publishingId,
                                                    final int topicId,
                                                    final int authorId) {
        List<Article> articles =
            articleService.getArticlesBytopicAndPublishingAndAuthorId(topicId,
                publishingId, authorId);
        Map<Integer, String> articlesMap = new HashMap<>();
        for (Article a : articles) {
            if (articleIsApproved(a)) {
                articlesMap.put(a.getId(), a.getTitle());
            }
        }
        return articlesMap;
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
