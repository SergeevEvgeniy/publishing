package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Сервис, содержащий бизнес-логику для работы со статьями.
 */
public interface ArticleService {
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
    List<ArticleInfo> getArticleInfoList(HttpServletRequest request);

    /**
     * Получение журнала/газеты из сервиса Publishing.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return объект класса {@link Publishing}
     */
    Publishing getPublishingById(int publishingId);

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
    ArticleForm getNewArticleForm();

    /**
     * Получение сотрудника из сервиса Employee по его идентификатору.
     *
     * @param id идентификатор сотрудника
     * @return сотрудник, объект класса {@link Employee}
     */
    Employee getAuthorById(int id);

    /**
     * Создание объекта {@link ArticleForm} и заполнение его данными. которые потом будут редактироваться.
     *
     * @param articleId идентификатор статьи
     * @return {@link ArticleForm}
     */
    ArticleForm getUpdateArticleFormByArticleId(int articleId);
}
