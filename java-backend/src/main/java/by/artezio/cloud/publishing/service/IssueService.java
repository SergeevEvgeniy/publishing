package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Issue;

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

}
