package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.IssueArticle;

import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 * @author Igor Kuzmin
 */
public interface IssueService {

    /**
     * Получение списка всех номеров.
     * @return {@link List} всех {@link Issue};
     * */
    List<Issue> getListOfAllIssues();

    /**
     * Удаление номера по идентификатору.
     * @param id - идентификатор {@link Issue}
     * */
    void deleteIssueById(final int id);

    /**
     * Получение {@link by.artezio.cloud.publishing.dto.IssueForm}
     * по идентификатору {@link Issue}.
     * @param issueId идентификатор {@link Issue}
     * @return {@link by.artezio.cloud.publishing.dto.IssueForm}
     */
    Issue getIssueById(final int issueId);

    /**
     * Метод получения списка {@link by.artezio.cloud.publishing.dto.IssueForm}
     * по id журнала/газеты.
     * @param publishingId - id {@link by.artezio.cloud.publishing.domain.Publishing}
     * @return список {@link by.artezio.cloud.publishing.dto.IssueForm}
     * */
    List<Issue> getIssueListByPublishingId(final int publishingId);

    /**
     * Получение списка сущностей {@link IssueArticle}
     * по id {@link Issue}.
     * @param issueId - id {@link Issue}.
     * @return список {@link IssueArticle}.
     * */
    List<IssueArticle> getIssueArticleListByIssueId(final int issueId);

}
