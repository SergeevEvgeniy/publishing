package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.dto.TopicShortInfo;
import by.artezio.cloud.publishing.dto.IssueView;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.ArticleService;
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

    private ReviewService reviewService;

    /**
     * @param issueService - {@link IssueService}
     * @param publishingService - {@link PublishingService}
     * @param securityService - {@link SecurityService}
     * @param articleService - {@link ArticleService}
     * @param reviewService - {@link ReviewService}
     * */
    public IssueWebFacade(final IssueService issueService,
                          final PublishingService publishingService,
                          final SecurityService securityService,
                          final ArticleService articleService,
                          final ReviewService reviewService) {

        this.issueService = issueService;
        this.publishingService = publishingService;
        this.securityService = securityService;
        this.articleService = articleService;
        this.reviewService = reviewService;
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
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
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
        List<IssueInfo> issueInfoList = new ArrayList<>();
        List<PublishingDTO> publishingList;
        if (user.isChiefEditor()) {
            publishingList = publishingService.getPublishingList();
        } else {
            publishingList =
                publishingService.getPublishingListByEmployeeId(user.getId());
        }
        for (PublishingDTO p : publishingList) {
            List<IssueInfo> listForPublishing =
                issueService.getIssueListByPublishingId(p.getId());
            for (IssueInfo issueInfo : listForPublishing) {
                issueInfo.setPublishingTitle(p.getTitle());
                int numberOfArticles =
                    issueService.getArticleIdList(issueInfo.getIssueId()).size();
                issueInfo.setNumberOfArticle(numberOfArticles);
                issueInfoList.add(issueInfo);
            }
        }
        return issueInfoList;
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
    public IssueView getIssueViewByIssueId(final int issueId) {
        securityService.checkIsEditor();
        IssueView issueView = issueService.getIssueViewByIssueId(issueId);
        List<Integer> articleIdList = issueService.getArticleIdList(issueId);
        List<Article> articles = new ArrayList<>();
        for (Integer id : articleIdList) {
            articles.add(articleService.getArticleById(id));
        }
        issueView.setArticles(articles);
        issueView.setAdvertisingPath(issueService.getAdvertisingFilePath(issueId));
        PublishingDTO publishing =
            publishingService.getPublishingById(issueView.getPublishingId());
        issueView.setPublishingTitle(publishing.getTitle());
        return issueView;
    }

    /**
     * Метод для получения списка который содержит
     * информацию о тематиках {@link by.artezio.cloud.publishing.domain.Topic}
     * для данного {@link PublishingDTO}. Предназначена лля
     * выпадающего списка на форме добавления/редактирования
     * номеров.
     * @param publishingId - id {@link PublishingDTO}
     * @return список {@link by.artezio.cloud.publishing.domain.Topic}
     * */
    public List<TopicShortInfo> getTopicListByPublishingId(final int publishingId) {
        return publishingService.getTopicsByPublishingId(publishingId);
    }

    /**
     * Получение списка допущенных в публикацию авторов.
     * @param publishingId - id {@link PublishingDTO}.
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
     * @return список {@link Employee}.
     * */
    public List<Employee> getApprovedJournalist(final int publishingId,
                                                final int topicId) {
        List<Employee> journalists =
            publishingService.getPublishingJournalistByPublishingId(publishingId);
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
     * @param topicId - id {@link by.artezio.cloud.publishing.domain.Topic}.
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
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    public void deleteIssueById(final int issueId) {
        issueService.deleteIssueById(issueId);
    }

    /**
     * Метод для создания нового номера и сохранеия его в бд.
     * @param issueForm - {@link IssueForm}.
     * */
    public void createNewIssue(final IssueForm issueForm) {
        issueService.createNewIssue(issueForm);
    }

    /**
     * Метод для обновления и сохранения в бд информации по уже существующему номеру.
     * @param issueForm - {@link IssueForm}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}.
     * */
    public void updateIssue(final Integer issueId, final IssueForm issueForm) {
        issueService.updateIssue(issueId, issueForm);
    }

    /**
     * Данный метод предназначен для преобразования IssueForm в IssueView.
     * Данное преобразование необходимо в том случаи, когда пользователь
     * заполнил форму но она не прошла валидацию. И чтобы отобразить состояние
     * формы до отправки, мы на основе ранее введенных данных формируем объект
     * dto для отображения {@link IssueView}.
     * @param issueForm - {@link IssueForm}.
     * @return - {@link IssueView}.
     * */
    public IssueView mapIssueFormToIssueView(final IssueForm issueForm) {
        IssueView issueView = new IssueView();
        List<Article> articles = new ArrayList<>();
        if (issueForm.getArticlesId() != null) {
            for (Integer id : issueForm.getArticlesId()) {
                articles.add(articleService.getArticleById(id));
            }
            issueView.setArticles(articles);
        }
        issueView.setAdvertisingPath(issueForm.getAdvertisingPath());
        Integer publishingId = issueForm.getPublishingId();
        if (publishingId != null) {
            PublishingDTO publishing =
                publishingService.getPublishingById(issueForm.getPublishingId());
            issueView.setPublishingTitle(publishing.getTitle());
            issueView.setPublishingId(publishing.getId());
        }
        issueView.setNumber(issueForm.getNumber());
        issueView.setLocalDate(issueForm.getLocalDate());
        return issueView;
    }


}
