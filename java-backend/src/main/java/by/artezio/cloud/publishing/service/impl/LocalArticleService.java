package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.ArticleCoauthorsDao;
import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.PublishingDTO;
import by.artezio.cloud.publishing.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис, содержащий бизнес-логику по обработке статей.
 */
@Service
public class LocalArticleService implements by.artezio.cloud.publishing.service.ArticleService {

    private ArticleDao articleDao;
    private TopicDao topicDao;
    private PublishingDao publishingDao;
    private EmployeeDao employeeDao;
    private ArticleCoauthorsDao coauthorsDao;

    /**
     * Конструктор с параметрами.
     *
     * @param articleDao    класс для взаимодействия с таблицей article
     * @param topicDao      класс для взаимодействия с таблицей topic
     * @param publishingDao класс для взаимодействия с таблицей publishing
     * @param employeeDao   класс для взаимодействия с таблицей employee
     * @param coauthorsDao  класс для взаимодействия с таблицей article_coauthors
     */
    @Autowired
    public LocalArticleService(final ArticleDao articleDao,
                               final TopicDao topicDao,
                               final PublishingDao publishingDao,
                               final EmployeeDao employeeDao,
                               final ArticleCoauthorsDao coauthorsDao) {
        this.articleDao = articleDao;
        this.topicDao = topicDao;
        this.publishingDao = publishingDao;
        this.employeeDao = employeeDao;
        this.coauthorsDao = coauthorsDao;
    }

    /**
     * Пустой конструктор.
     */
    public LocalArticleService() {
    }

    /**
     * Получение списка объектов {@link ArticleInfo}.
     *
     * <p>
     * Используется для получения данных в контроллере
     * {@link by.artezio.cloud.publishing.web.controllers.ArticleController}</p>
     *
     * @return список объектов класса {@link ArticleInfo}
     */
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
            Topic topic = topicDao.getTopicById(topicId);
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

    /**
     * Получение журнала/газеты из сервиса PublishingDTO.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return объект класса {@link PublishingDTO}
     */
    @Override
    public PublishingDTO getPublishingById(final int publishingId) {
        return publishingDao.getPublishingById(publishingId);
    }

    /**
     * Получение сотрудника из сервиса Employee по его идентификатору.
     *
     * @param id идентификатор сотрудника
     * @return сотрудник, объект класса {@link Employee}
     */
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

    /**
     * Создание объекта {@link ArticleForm} и заполнение его данными. которые потом будут редактироваться.
     *
     * @param articleId идентификатор статьи
     * @return {@link ArticleForm}
     */
    @Override
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        ArticleForm form = new ArticleForm();

        Article article = articleDao.getArticleByArticleId(articleId);

        PublishingDTO publishing = publishingDao.getPublishingById(article.getPublishingId());
        List<PublishingDTO> publishings = new ArrayList<>();
        publishings.add(publishing);

        List<Topic> topics = topicDao.getTopicsByPublishingId(article.getPublishingId());

        List<Integer> coauthorsIdList = coauthorsDao.getCoauthorsIdByArticleId(articleId);
        List<Employee> coauthors = new ArrayList<>(coauthorsIdList.size());
        for (Integer id : coauthorsIdList) {
            coauthors.add(employeeDao.getEmployeeById(id));
        }

        Employee author = employeeDao.getEmployeeById(article.getAuthorId());

        List<Employee> publishingEmployees = employeeDao.getEmployeesByPublishingId(article.getPublishingId());

        publishingEmployees.remove(author);

        List<Employee> employees = new ArrayList<>(publishingEmployees.size());

        for (Employee e : publishingEmployees) {
            if (!coauthors.contains(e)) {
                employees.add(e);
            }
        }

        List<Review> reviews = articleDao.getReviewsByArticleId(articleId);
        Map<Employee, Review> reviewMap = new HashMap<>(reviews.size());
        for (Review r : reviews) {
            Employee e = employeeDao.getEmployeeById(r.getReviewerId());
            reviewMap.put(e, r);
        }

        form.setPublishing(publishings);
        form.setContent(article.getContent());
        form.setTopics(topics);
        form.setTitle(article.getTitle());
        form.setCurrentCoauthors(coauthors);
        form.setAvailableCoauthors(employees);
        form.setReviews(reviewMap);

        return form;
    }

    @Override
    public Article getArticleById(final int articleId) {
        return articleDao.getArticleByArticleId(articleId);
    }

    @Override
    public void deleteArticle(final Article article) {
        articleDao.deleteArticleById(article.getId());
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
}
