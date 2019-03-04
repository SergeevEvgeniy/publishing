package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.ArticleDao;
import by.artezio.cloud.publishing.dao.TopicDao;
import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
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

    private final ArticleDao articleDao;
    private final TopicDao topicDao;

    /**
     * Конструктор для создания объекта с указанными значениями полей.
     *
     * @param articleDao объект класса {@link ArticleDao} для взаимодействия с
     * таблицей "article"
     * @param topicDao объект класса {@link TopicDao} для взаимодействия с
     * таблицей "topic"
     */
    @Autowired
    public ArticleService(final ArticleDao articleDao, final TopicDao topicDao) {
        this.articleDao = articleDao;
        this.topicDao = topicDao;
    }

    /**
     * Получение списка объектов {@link ArticleInfo}.
     *
     * <p>
     * Используется для получения данных в контроллере
     * {@link ArticleController}</p>
     *
     * @param request запрос пользователя, объект класса
     * {@link HttpServletRequest}
     * @return список объектов класса {@link ArticleInfo}
     */
    public List<ArticleInfo> getArticleInfoList(final HttpServletRequest request) {
        List<ArticleInfo> articleLists = new ArrayList<>();

        Cookie[] cookies = request.getCookies();
        String email = "";

        if (cookies != null
                || cookies.length != 0) {
            for (Cookie c : cookies) {
                if ("user".equalsIgnoreCase(c.getName())) {
                    email = c.getValue();
                }
            }
        }

        int id = getUserByEmail(email);
        List<Article> articles = articleDao.getArticleListByJournalistId(id);

        for (Article a : articles) {
            ArticleInfo aListDto = new ArticleInfo();
            aListDto.setTitle(a.getTitle());

            String publishing = a.getPublishing().getTitle();
            aListDto.setPublishing(publishing);

            String topic = a.getTopic().getName();
            aListDto.setTopic(topic);

            Employee author = a.getAuthor();

            String authorFullName = getEmployeeShortName(author);

            aListDto.setAuthorFullName(authorFullName);

            Set<String> coauthors = getCoauthorsShortNames(a.getCoauthors());
            aListDto.setCoauthors(coauthors);
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
     * новой, или редактировании старой статьи.
     *
     * @param request запрос пользователя, объект класса
     * {@link HttpServletRequest}
     * @return объект класса {@link ArticleForm} с данными для заполнения формы
     * на странице update_article.jsp
     */
    public ArticleForm getArticleForm(final HttpServletRequest request) {

        String action = request.getParameter("action");
        ArticleForm dto = new ArticleForm();

        if ("create".equalsIgnoreCase(action)) {
            dto = createNewArticleDto();
        } else {
            dto = prepareArticleDtoForUpdate(request);
        }

        return null;
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
     * ({@link Set}&lt;{@link Employee}&gt;)
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

    /**
     * Получение списка соавторов по идентификатору статьи.
     *
     * @param id идентификатор статьи
     * @return список сотрудников {@link Employee}, которые являются соавторами
     * статьи.
     */
    private List<Employee> getCoauthorsByArticleId(final int id) {
        List<ArticleCoauthor> coautors = articleDao.getArticleCoauthorsByArticleId(id);

        // Тут будут запросы к сервису employee
        return null;
    }

    /**
     * Получение сотрудника по его email.
     *
     * @param userEmail email адрес сотрудника.
     * @return сотрудник, {@link Employee}
     */
    private int getUserByEmail(final String userEmail) {
        // Тут будут запросы к сервисам Employee
        return 0;
    }

    /**
     * Создание объекта {@link ArticleForm} для обновления существующей статьи.
     *
     * @param request запрос пользователя, объект класса
     * {@link HttpServletRequest}
     * @return объект {@link ArticleForm} с детальными данными о статье
     */
    private ArticleForm prepareArticleDtoForUpdate(final HttpServletRequest request) {
        return null;
    }

    /**
     * Создание объекта {@link ArticleForm} для создания новой статьи.
     *
     * @return объект {@link ArticleForm} не содержащий данных о статье.
     */
    private ArticleForm createNewArticleDto() {
        ArticleForm dto = new ArticleForm();
        List<Publishing> publishings = getAllPublishings();
        List<String> publishingNames = new ArrayList<>();

        for (Publishing p : publishings) {
            publishingNames.add(p.getTitle());
        }

        dto.setPublishings(publishingNames);
        return dto;
    }

    /**
     * Получение списка журналов/газет из сервиса Publishing.
     *
     * @return список журналов/газет ({@link List}&lt;{@link Publishing}&gt;)
     */
    private List<Publishing> getAllPublishings() {
        // Запросы к сервисам с Publishing
        return null;
    }
}
