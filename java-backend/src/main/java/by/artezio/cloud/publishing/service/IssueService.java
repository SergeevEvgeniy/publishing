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

}
