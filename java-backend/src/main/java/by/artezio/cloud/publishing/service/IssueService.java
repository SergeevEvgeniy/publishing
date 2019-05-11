package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.IssueView;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 *
 * @author Igor Kuzmin
 */
public interface IssueService {


    /**
     * Удаление номера по идентификатору.
     *
     * @param id - идентификатор {@link Issue}
     * @return - удаленный объект {@link Issue}.
     */
    Issue deleteIssueById(final int id);

    /**
     * Получение {@link by.artezio.cloud.publishing.dto.IssueForm}
     * по идентификатору {@link Issue}.
     *
     * @param issueId идентификатор {@link Issue}
     * @return {@link by.artezio.cloud.publishing.dto.IssueForm}
     */
    IssueView getIssueViewByIssueId(final int issueId);

    /**
     * Получение объекта dto {@link IssueForm} по id {@link Issue}.
     * В данном случаи {@link IssueForm} несет информацию для заполнения формы
     * данными о {@link Issue}.
     * @param issueId - id {@link Issue}.
     * @return - {@link IssueForm}.
     * */
    IssueForm getIssueFormByIssueId(final int issueId);

    /**
     * Метод получения списка {@link by.artezio.cloud.publishing.dto.IssueForm}
     * по id журнала/газеты.
     *
     * @param publishingId - id {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     * @return список {@link by.artezio.cloud.publishing.dto.IssueForm}
     */
    List<IssueInfo> getIssueListByPublishingId(final int publishingId);

    /**
     * Получение списка сущностей {@link by.artezio.cloud.publishing.domain.IssueArticle}
     * по id {@link Issue}.
     *
     * @param issueId - id {@link Issue}.
     * @return список {@link by.artezio.cloud.publishing.domain.IssueArticle}.
     */
    List<Integer> getArticleIdList(final int issueId);

    /**
     * Возвращает все номера, дата публикации которых равна date.
     *
     * @param date Дата выпуска номером
     * @return Список номеров
     */
    List<Issue> getIssuesByDate(LocalDate date);

    /**
     * Метод для создания и сохранения в бд информации для нового номера.
     *
     * @param issueForm - {@link IssueForm}.
     * @return - id созданного {@link Issue}.
     */
    Integer createNewIssue(final IssueForm issueForm);

    /**
     * Метод для обновления и сохранения в бд информации по уже существующему номеру.
     *
     * @param issueId   - id {@link Issue}.
     * @param issueForm - {@link IssueForm}.
     */
    void updateIssue(final Integer issueId, final IssueForm issueForm);

    /**
     * Получение {@link Issue} по идентификатору {@link Issue}.
     *
     * @param issueId идентификатор {@link Issue}
     * @return {@link Issue}
     */
    Issue getIssueById(final int issueId);

    /**
     * Проверка, является статья опубликованной.
     *
     * @param articleId id статьи
     * @return {@code true}, если статья уже опубликована, иначе - {@code false}
     */
    boolean isArticlePublished(int articleId);

    /**
     * Метод для удаления из таблицы issue_article записей, связанных с указанной статьёй.
     *
     * @param articleId id статьи
     */
    void deleteIssueArticleByArticleId(int articleId);
}
