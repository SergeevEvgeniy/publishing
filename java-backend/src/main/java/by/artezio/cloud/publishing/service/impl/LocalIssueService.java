package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.IssueDao;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 * @author Igor Kuzmin
 */
@Service
public class LocalIssueService implements IssueService {

    private IssueDao issueDao;

    private PublishingService publishingService;

    /**
     * Конструктор с параметрами.
     * @param issueDao {@link IssueDao}
     * @param publishingService {@link PublishingService}
     * */
    public LocalIssueService(final IssueDao issueDao,
                             final PublishingService publishingService) {
        this.issueDao = issueDao;
        this.publishingService = publishingService;
    }

    @Override
    public List<Issue> getListOfAllIssues() {
        return issueDao.getListOfAllIssues();
    }

    @Override
    public List<IssueInfo> getListOfAllIssueInfo() {
        List<Issue> issueList = issueDao.getListOfAllIssues();
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issueList) {
            Publishing publishing = publishingService.getPublishingById(issue.getId());
            IssueInfo issueInfo = new IssueInfo();
            issueInfo.setPublishingId(issue.getPublishingId());
            issueInfo.setPublishingTitle(publishing.getTitle());
            issueInfo.setNumber(issue.getNumber());
            issueInfo.setLocalDate(issue.getDate());
            issueInfo.setPublished(issue.isPublished());
            issueInfoList.add(issueInfo);
        }
        return issueInfoList;
    }

}
