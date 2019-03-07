package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сервис, содержащий бизнес-логику по обработке статей.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PublishingService publishingService;

    @Autowired
    private EmployeeService employeeService;

//    /**
//     * Конструктор для создания объекта с указанными значениями полей.
//     *
//     * @param articleDao объект класса {@link ArticleDao} для взаимодействия с
//     * таблицей "article"
//     * @param topicDao объект класса {@link TopicDao} для взаимодействия с
//     * таблицей "topic"
//     */
//    @Autowired
//    public ArticleService(final ArticleDao articleDao, final TopicDao topicDao) {
//        this.articleDao = articleDao;
//        this.topicDao = topicDao;
//    }

    /**
     * Получение списка объектов {@link ArticleInfo}.
     *
     * <p>
     * Используется для получения данных в контроллере
     * {@link by.artezio.cloud.publishing.web.controllers.ArticleController}</p>
     *
     * @param request запрос пользователя, объект класса
     *                {@link HttpServletRequest}
     * @return список объектов класса {@link ArticleInfo}
     */
    public List<ArticleInfo> getArticleInfoList(final HttpServletRequest request) {
        List<ArticleInfo> articleLists = new ArrayList<>();

        Employee empl = (Employee) request.getSession().getAttribute("user");

        List<Article> articles = articleDao.getArticleListByJournalistId(empl.getId());

        for (Article a : articles) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setArticleId(a.getId());
            articleInfo.setTitle(a.getTitle());

            String publishing = a.getPublishing().getTitle();
            articleInfo.setPublishing(publishing);

            String topic = a.getTopic().getName();
            articleInfo.setTopic(topic);

            Employee author = a.getAuthor();

            String authorFullName = getEmployeeShortName(author);

            articleInfo.setAuthorFullName(authorFullName);

            Set<String> coauthors = getCoauthorsShortNames(a.getCoauthors());
            articleInfo.setCoauthors(coauthors);
        }

        return articleLists;
    }

    /**
     * Получение журнала/газеты из сервиса Publishing.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return объект класса {@link Publishing}
     */
    public Publishing getPublishingById(final int publishingId) {
        // Тут будут запросы к сервисам Publishing
        return null;
    }

    /**
     * Получение объекта с данными.
     *
     * <p>
     * Используется для заполнения формы и для хранения данных при создании
     * новой статьи.
     *
     * @return объект класса {@link ArticleForm} с данными для заполнения формы
     * на странице update_article.jsp
     */
    public ArticleForm getNewArticleForm() {
        ArticleForm af = new ArticleForm();
        af.setPublishings(publishingService.getPublishingList());
        return af;
    }

    /**
     * Получение сотрудника из сервиса Employee по его идентификатору.
     *
     * @param id идентификатор сотрудника
     * @return сотрудник, объект класса {@link Employee}
     */
    public Employee getAuthorById(final int id) {
        return null;
    }

    /**
     * Получение множества ФИО соавторов.
     *
     * @param coauthors множество соавторов
     *                  ({@link Set}&lt;{@link Employee}&gt;)
     * @return множество ФИО соавторов ({@link Set}&lt;{@link String}&gt;)
     */
    private Set<String> getCoauthorsShortNames(final Set<Employee> coauthors) {
        Set<String> result = new HashSet<>();

        for (Employee e : coauthors) {
            result.add(getEmployeeShortName(e));
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

//    /**
//     * Получение списка соавторов по идентификатору статьи.
//     *
//     * @param id идентификатор статьи
//     * @return список сотрудников {@link Employee}, которые являются соавторами
//     * статьи.
//     */
//    private List<Employee> getCoauthorsByArticleId(final int id) {
//        List<ArticleCoauthor> coautors = articleDao.getArticleCoauthorsByArticleId(id);
//
//        // Тут будут запросы к сервису employee
//        return null;
//    }

    private List<Employee> getArticleCoauthorsByArticleId(final int articleId) {
        List<ArticleCoauthor> list = articleDao.getArticleCoauthorsByArticleId(articleId);
        List<Employee> employees = new ArrayList<>();
        for (ArticleCoauthor ac : list) {
            employees.add(employeeService.getEmployeeById(ac.getEmployeeId()));
        }
        return employees;
    }

    /**
     * Создание объекта {@link ArticleForm} и заполнение его данными. которые потом будут редактироваться.
     *
     * @param articleId идентификатор статьи
     * @return {@link ArticleForm}
     */
    public ArticleForm getUpdateArticleFormByArticleId(final int articleId) {
        ArticleForm form = new ArticleForm();
        Article article = articleDao.getArticleByArticleId(articleId);

        List<Publishing> publishings = new ArrayList<>();
        publishings.add(article.getPublishing());

        List<Topic> topics = new ArrayList<>();
        topics.add(article.getTopic());

        Set<Employee> publishingEmployees = employeeService.getEmployeesByPublishingId(article.getPublishing().getId());
        Set<Employee> coauthors = article.getCoauthors();
        publishingEmployees.remove(article.getAuthor());
        for (Employee e : publishingEmployees) {
            if (coauthors.contains(e)) {
                publishingEmployees.remove(e);
            }
        }
        List<Review> reviews = articleDao.getReviewsByArticleId(articleId);

        form.setPublishings(publishings);
        form.setContent(article.getContent());
        form.setTopics(topics);
        form.setTitle(article.getTitle());
        form.setCurrentCoauthors(article.getCoauthors());
        form.setAvailableCoauthors(publishingEmployees);
        form.setReviews(reviews);

        return form;
    }
}
