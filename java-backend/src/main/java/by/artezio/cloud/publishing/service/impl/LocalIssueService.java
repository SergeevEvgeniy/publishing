package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.IssueDao;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.service.IssueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 * @author Igor Kuzmin
 */
@Service
public class LocalIssueService implements IssueService {

    private IssueDao issueDao;

    /**
     * Конструктор с параметрами.
     * @param issueDao {@link IssueDao}
     * */
    public LocalIssueService(final IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    @Override
    public List<Issue> getListOfAllIssues() {
        return issueDao.getListOfAllIssues();
    }

}
