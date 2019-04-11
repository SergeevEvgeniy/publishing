package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.IssueArticleDao;
import by.artezio.cloud.publishing.dao.IssueDao;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.IssueArticle;
import by.artezio.cloud.publishing.service.IssueService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 * @author Igor Kuzmin
 */
@Service
public class LocalIssueService implements IssueService {

    private IssueDao issueDao;

    private IssueArticleDao issueArticleDao;

    /**
     * Конструктор с параметрами.
     * @param issueDao {@link IssueDao}
     * @param issueArticleDao {@link IssueArticleDao}
     * */
    public LocalIssueService(final IssueDao issueDao,
                             final IssueArticleDao issueArticleDao) {
        this.issueDao = issueDao;
        this.issueArticleDao = issueArticleDao;
    }

    @Override
    public List<Issue> getListOfAllIssues() {
        return issueDao.getListOfAllIssues();
    }

    @Override
    public Issue getIssueById(final int issueId) {
        return issueDao.getIssueById(issueId);
    }

    @Override
    public void deleteIssueById(final int id) {
        issueDao.deleteIssueById(id);
    }

    @Override
    public List<Issue> getIssueListByPublishingId(final int publishingId) {
        return issueDao.getIssueListByPublishingId(publishingId);
    }

    @Override
    public List<Integer> getArticleIdListByIssueId(final int issueId) {
        List<IssueArticle> issueArticleList =
            issueArticleDao.getIssueArticleListByIssueId(issueId);
        List<Integer> articleIdList = new ArrayList<>();
        for (IssueArticle ia : issueArticleList) {
            articleIdList.add(ia.getArticleId());
        }
        return articleIdList;
    }

    @Override
    public List<Issue> getIssuesByDate(final LocalDate date) {
        return issueDao.getIssueByDate(date);
    }

}
