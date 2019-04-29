package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.dto.ArticleDto;
import by.artezio.cloud.publishing.dto.ArticleFilter;
import by.artezio.cloud.publishing.dto.ArticleForm;
import by.artezio.cloud.publishing.dto.ArticleInfo;
import by.artezio.cloud.publishing.dto.AuthorFilter;
import by.artezio.cloud.publishing.dto.User;

import java.util.List;
import java.util.Map;

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
    ArticleDto getArticleDtoById(int articleId);

    /**
     * Метод для удаления статьи.
     *
     * @param articleId {@link Integer} id статьи, которую нужно удалить
     */
    void deleteArticle(Integer articleId);

    /**
     * Получение статей по id рубрики и журнала.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    List<ArticleDto> getArticleByTopicAndPublishingId(int topicId, int publishingId);

    /**
     * Получение статей по id рубрики, журнала и автора.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @param authorId     id автора
     * @return {@link List} of {@link Article}
     */
    List<Article> getArticlesBytopicAndPublishingAndAuthorId(int topicId, int publishingId, int authorId);

    /**
     * Получение списка {@link ArticleCoauthor}.
     *
     * @param articleId id статьи
     * @return Список объектов {@link ArticleCoauthor}
     */
    List<ArticleCoauthor> getCoauthorsByArticleId(int articleId);

    /**
     * Сохранение статьи, присланной со страницы /article/new.
     *
     * @param articleForm {@link ArticleForm}
     */
    void save(ArticleForm articleForm);

    /**
     * Обновление статьи с указанным id.
     *
     * @param articleForm форма, хранящая информацию о статье
     * @param articleId   id обновляемой статьи
     */
    void update(ArticleForm articleForm, Integer articleId);

    /**
     * @param publishingId id журнала
     * @param topicId      id рубрики
     * @param authorId     id автора
     * @return {@link List} of {@link ArticleDto}, список неопубликованных статей
     */
    List<ArticleDto> getUnpublishedArticles(int publishingId, int topicId, int authorId);

    /**
     * @param authorId id автора
     * @return количество статей указанного автора
     */
    int getArticleCountByAuthorId(int authorId);

    /**
     * @param authorId id автора
     * @return Карта пар 'Название журнала - количество статей' для указанного автора
     */
    Map<String, Integer> getArticleCountByPublishingMap(int authorId);


    /**
     * @param authorId id автора
     * @return Карта пар 'Название рубрики - количество статей' для указанного автора
     */
    Map<String, Integer> getArticleCountByTopicMap(int authorId);

    /**
     * @param filter объект, хранящий в своих полях параметры get запроса
     * @return DTO для статьи
     */
    List<ArticleDto> getArticleDtoList(ArticleFilter filter);

    /**
     * @param articleId id статьи
     * @return DTO для статьи
     */
    ArticleDto getArticleDto(int articleId);

    /**
     * @param articleId id статьи
     * @return {@code true}, если статья с данным id существует
     */
    boolean isArticleExists(int articleId);

    /**
     * Получить список id авторов из указанного журнала, у которых есть статья с указанной рубрикой.
     *
     * @param filter объект, хранящий в себе параметры get запроса
     * @return список id авторов, у которых есть статья, проходящая фильтр
     */
    List<Integer> getAuthorsIdList(AuthorFilter filter);
}
