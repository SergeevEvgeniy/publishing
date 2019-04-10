package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.User;

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
     * @param user {@link User}
     * @return список объектов класса {@link ArticleInfo}
     */
    List<ArticleInfo> getArticleInfoList(User user);

    /**
     * Получение журнала/газеты из сервиса Publishing.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return объект класса {@link Publishing}
     */
    Publishing getPublishingById(int publishingId);

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

    /**
     * Получение статьи по ее идентификатору.
     *
     * @param articleId - id статьи
     * @return {@link Article}
     */
    Article getArticleById(int articleId);

    /**
     * Метод для удаления статьи.
     *
     * @param article {@link Article} статья, которую нужно удалить
     */
    void deleteArticle(Article article);

    /**
     * Получение статей по id рубрики и журнала.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    List<Article> getArticleByTopicAndPublishingId(int topicId, int publishingId);

    /**
     * Получение статей по id рубрики, журнала и автора.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @param authorId     id автора
     * @return {@link List} of {@link Article}
     */
    List<Article> getArticlesBytopicAndPublishingAndAuthorId(int topicId, int publishingId, int authorId);
}
