package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.ArticleCoauthorsDao;
import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.dao.ReviewDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.PublishingService;
import by.artezio.cloud.publishing.service.TopicService;
import by.artezio.cloud.publishing.service.converter.ArticleFormToArticleConverter;
import by.artezio.cloud.publishing.service.converter.ArticleToArticleDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис, содержащий бизнес-логику по обработке статей.
 */
@Service
public class LocalArticleService implements by.artezio.cloud.publishing.service.ArticleService {

    private ArticleDao articleDao;
    private TopicService topicService;
    private PublishingDao publishingDao;
    private PublishingService publishingService;
    private EmployeeDao employeeDao;
    private ArticleCoauthorsDao coauthorsDao;
    private ReviewDao reviewDao;
    private ArticleFormToArticleConverter articleConverter;
    private ArticleToArticleDtoConverter articleToArticleDtoConverter;

    /**
     * @param articleDao класс для взаимодействия с таблицей article
     */
    @Autowired
    public void setArticleDao(final ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /**
     * @param topicService класс для взаимодействия с таблицей topic
     */
    @Autowired
    public void setTopicService(final TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * @param publishingDao класс для взаимодействия с таблицей publishing
     */
    @Autowired
    public void setPublishingDao(final PublishingDao publishingDao) {
        this.publishingDao = publishingDao;
    }

    /**
     * @param publishingService {@link PublishingService}
     */
    @Autowired
    public void setPublishingService(final PublishingService publishingService) {
        this.publishingService = publishingService;
    }

    /**
     * @param employeeDao класс для взаимодействия с таблицей employee
     */
    @Autowired
    public void setEmployeeDao(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * @param coauthorsDao класс для взаимодействия с таблицей article_coauthors
     */
    @Autowired
    public void setCoauthorsDao(final ArticleCoauthorsDao coauthorsDao) {
        this.coauthorsDao = coauthorsDao;
    }

    /**
     * @param reviewDao {@link ReviewDao}
     */
    @Autowired
    public void setReviewDao(final ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    /**
     * @param articleConverter {@link ArticleFormToArticleConverter}
     */
    @Autowired
    public void setArticleConverter(final ArticleFormToArticleConverter articleConverter) {
        this.articleConverter = articleConverter;
    }

    /**
     * @param articleToArticleDtoConverter {@link ArticleToArticleDtoConverter}
     */
    @Autowired
    public void setArticleToArticleDtoConverter(final ArticleToArticleDtoConverter articleToArticleDtoConverter) {
        this.articleToArticleDtoConverter = articleToArticleDtoConverter;
    }

    @Override
    public List<ArticleInfo> getArticleInfoList(final User user) {
        List<ArticleInfo> articleLists = new ArrayList<>();

        List<Article> articles;
        if (user.getType().equals('J')) {
            articles = articleDao.getArticleListByJournalistId(user.getId());
        } else {
            articles = articleDao.getAllArticles();
        }
        for (Article a : articles) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setArticleId(a.getId());
            articleInfo.setTitle(a.getTitle());

            Integer publishingId = a.getPublishingId();
            PublishingDTO publishing = publishingDao.getPublishingById(publishingId);

            articleInfo.setPublishingName(publishing.getTitle());

            Integer topicId = a.getTopicId();
            Topic topic = topicService.getTopicById(topicId);
            String topicName = topic.getName();
            articleInfo.setTopic(topicName);

            Integer authorId = a.getAuthorId();
            Employee author = employeeDao.getEmployeeById(authorId);

            String authorFullName = getEmployeeShortName(author);

            articleInfo.setAuthorFullName(authorFullName);
            List<Integer> coauthorsId = coauthorsDao.getCoauthorsIdByArticleId(a.getId());

            List<Employee> coauthorsList = new ArrayList<>(coauthorsId.size());
            for (Integer id : coauthorsId) {
                coauthorsList.add(employeeDao.getEmployeeById(id));
            }

            List<String> coauthors = getCoauthorsShortNames(coauthorsList);
            articleInfo.setCoauthors(coauthors);
            articleInfo.setPublished(articleDao.isPublished(a.getId()));
            articleLists.add(articleInfo);
        }

        return articleLists;
    }

    @Override
    public PublishingDTO getPublishingById(final int publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }

    @Override
    public Employee getAuthorById(final int id) {
        return employeeDao.getEmployeeById(id);
    }

    /**
     * Получение множества ФИО соавторов.
     *
     * @param coauthors множество соавторов
     *                  ({@link List}&lt;{@link Employee}&gt;)
     * @return множество ФИО соавторов ({@link List}&lt;{@link String}&gt;)
     */
    private List<String> getCoauthorsShortNames(final List<Employee> coauthors) {
        List<String> result = new ArrayList<>(coauthors.size());

        if (coauthors != null) {
            for (Employee e : coauthors) {
                result.add(getEmployeeShortName(e));
            }
        }
        return result;
    }

    /**
     * Получение сокращённого полного имени сотрудника (Фамилия И. О.)
     * <p>
     * Если у сотрудника не указано отчество, то метод вернёт фамилию и первую
     * букву имени
     *
     * @param e сотрудник, объект класса {@link Employee}
     * @return ФИО в формате "Фамилия И. О."
     */
    private String getEmployeeShortName(final Employee e) {
        StringBuilder bldr = new StringBuilder()
            .append(e.getLastName())
            .append(" ")
            .append(e.getFirstName().charAt(0))
            .append(". ");
        if (e.getMiddleName() != null
            && !e.getMiddleName().isEmpty()) {
            bldr.append(e.getMiddleName().charAt(0))
                .append(".");
        }
        return bldr.toString();
    }

    @Override
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        ArticleForm form = new ArticleForm();

        Article article = articleDao.getArticleByArticleId(articleId);
        PublishingDTO publishing = publishingService.getPublishingById(article.getPublishingId());

        List<ArticleCoauthor> articleCoauthors = articleDao.getArticleCoauthorsByArticleId(articleId);
        List<Integer> coauthors = new ArrayList<>(articleCoauthors.size());
        for (ArticleCoauthor ac : articleCoauthors) {
            coauthors.add(ac.getEmployeeId());
        }

        form.setPublishingId(publishing.getId());
        form.setTitle(article.getTitle());
        form.setTopicId(article.getTopicId());
        form.setContent(article.getContent());
        form.setCoauthors(coauthors);
        return form;
    }

    @Override
    public Article getArticleById(final int articleId) {
        return articleDao.getArticleByArticleId(articleId);
    }

    @Override
    public void deleteArticle(final Integer articleId) {
        articleDao.deleteArticleById(articleId);
    }

    @Override
    public List<Article> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
        return articleDao.getArticleByTopicAndPublishingId(topicId, publishingId);
    }

    @Override
    public List<Article> getArticlesBytopicAndPublishingAndAuthorId(final int topicId,
                                                                    final int publishingId,
                                                                    final int authorId) {
        return articleDao.getArticleByTopicAndPublishingAndAuthorId(topicId, publishingId, authorId);
    }

    @Override
    public List<ArticleCoauthor> getCoauthorsByArticleId(final int articleId) {
        return articleDao.getArticleCoauthorsByArticleId(articleId);
    }

    @Override
    public List<Review> getReviewsByArticleId(final int articleId) {
        return reviewDao.getReviewsByArticleId(articleId);
    }

    @Override
    public void save(final ArticleForm articleForm) {
        Article article = articleConverter.convert(articleForm);
        List<Integer> coauthors = articleForm.getCoauthors();
        Integer articleId = articleDao.save(article);

        if (coauthors != null) {
            for (Integer coauthorId : coauthors) {
                coauthorsDao.save(articleId, coauthorId);
            }
        }
    }

    @Override
    public void update(final ArticleForm articleForm, final Integer articleId) {
        Article article = articleConverter.convert(articleForm);
        article.setId(articleId);
        articleDao.update(article, articleForm.getCoauthors());
    }

    @Override
    public List<ArticleDto> getUnpublishedArticles(final int publishingId,
                                                   final int topicId,
                                                   final int authorId) {
        List<Article> unpublishedArticles = articleDao.getUnpublishedArticles(publishingId, topicId, authorId);
        List<ArticleDto> unpublishedList = new ArrayList<>(unpublishedArticles.size());
        for (Article a : unpublishedArticles) {
            unpublishedList.add(articleToArticleDtoConverter.convert(a));
        }
        return unpublishedList;
    }

    @Override
    public int getArticleCountByAuthorId(final int authorId) {
        return articleDao.getArticleCountByAuthorId(authorId);
    }

    @Override
    public Map<String, Integer> getArticleCountByPublishingMap(final int authorId) {
        return articleDao.getCountByPublishingMap(authorId);
    }

    @Override
    public Map<String, Integer> getArticleCountByTopicMap(final int authorId) {
        return articleDao.getCountByTopicMap(authorId);
    }
}
