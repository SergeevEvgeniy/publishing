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
    public IssueInfo mapIssueToIssueInfo(final Issue issue) {
        Publishing publishing = publishingService.getPublishingById(issue.getPublishingId());
        IssueInfo issueInfo = new IssueInfo();
        issueInfo.setPublishingId(issue.getPublishingId());
        issueInfo.setPublishingTitle(publishing.getTitle());
        issueInfo.setNumber(issue.getNumber());
        issueInfo.setLocalDate(issue.getDate());
        issueInfo.setPublished(issue.isPublished());
        issueInfo.setIssueId(issue.getId());
        return issueInfo;
    }

    @Override
    public List<IssueInfo> mapIssueListToIssueInfoList(final List<Issue> issueList) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issueList) {
            IssueInfo issueInfo = mapIssueToIssueInfo(issue);
            issueInfoList.add(issueInfo);
        }
        return issueInfoList;
    }

    @Override
    public IssueInfo getIssueInfoByIssueId(final int issueId) {
        Issue issue = issueDao.getIssueById(issueId);
        return mapIssueToIssueInfo(issue);
    }

    @Override
    public List<IssueInfo> getListOfAllIssueInfo() {
        List<Issue> issueList = issueDao.getListOfAllIssues();
        return mapIssueListToIssueInfoList(issueList);
    }

    @Override
    public void deleteIssueById(final int id) {
        issueDao.deleteIssueById(id);
    }

    @Override
    public List<IssueInfo> getIssueInfoListByPublishingId(final int publishingId) {
        List<Issue> issueList = issueDao.getIssueListByPublishingId(publishingId);
        return mapIssueListToIssueInfoList(issueList);
    }
}
