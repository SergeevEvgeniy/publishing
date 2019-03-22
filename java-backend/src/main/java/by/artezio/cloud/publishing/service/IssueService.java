package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.IssueInfo;

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
     * Получение списка dto для раздела "номера".
     * @return {@link List} {@link IssueInfo}
     * */
    List<IssueInfo> getListOfAllIssueInfo();

    /**
     * Удаление номера по идентификатору.
     * @param id - идентификатор {@link Issue}
     * */
    void deleteIssueById(final int id);

    /**
     * Получение {@link IssueInfo} по идентификатору {@link Issue}.
     * @param issueId идентификатор {@link Issue}
     * @return {@link IssueInfo}
     */
    IssueInfo getIssueInfoByIssueId(final int issueId);

    /**
     * Функция преобразования сущности {@link Issue}
     * в объект dto {@link IssueInfo}.
     * @param issue сущность {@link Issue}
     * @return {@link IssueInfo}
     * */
    IssueInfo mapIssueToIssueInfo(final Issue issue);

}
