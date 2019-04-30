package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.AdvertisingDao;
import by.artezio.cloud.publishing.dao.IssueArticleDao;
import by.artezio.cloud.publishing.dao.IssueDao;
import by.artezio.cloud.publishing.domain.Advertising;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.IssueArticle;
import by.artezio.cloud.publishing.dto.IssueForm;
import by.artezio.cloud.publishing.dto.IssueInfo;
import by.artezio.cloud.publishing.dto.IssueView;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.converter.IssueFormToIssueConverter;
import by.artezio.cloud.publishing.service.converter.IssueToIssueInfoConverter;
import by.artezio.cloud.publishing.service.converter.IssueToIssueViewConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Issue}.
 *
 * @author Igor Kuzmin
 */
@Service
public class LocalIssueService implements IssueService {

    private IssueDao issueDao;

    private IssueArticleDao issueArticleDao;

    private AdvertisingDao advertisingDao;

    private IssueToIssueViewConverter toIssueViewConverter;

    private IssueToIssueInfoConverter toIssueInfoConverter;

    private IssueFormToIssueConverter toIssueConverter;

    /**
     * Конструктор с параметрами.
     *
     * @param issueDao             {@link IssueDao}
     * @param issueArticleDao      {@link IssueArticleDao}
     * @param advertisingDao       {@link AdvertisingDao}
     * @param toIssueConverter     {@link IssueFormToIssueConverter}
     * @param toIssueInfoConverter {@link IssueToIssueInfoConverter}
     * @param toIssueViewConverter {@link IssueToIssueViewConverter}
     */
    public LocalIssueService(final IssueDao issueDao,
                             final IssueArticleDao issueArticleDao,
                             final AdvertisingDao advertisingDao,
                             final IssueToIssueInfoConverter toIssueInfoConverter,
                             final IssueToIssueViewConverter toIssueViewConverter,
                             final IssueFormToIssueConverter toIssueConverter) {
        this.issueDao = issueDao;
        this.issueArticleDao = issueArticleDao;
        this.advertisingDao = advertisingDao;
        this.toIssueInfoConverter = toIssueInfoConverter;
        this.toIssueViewConverter = toIssueViewConverter;
        this.toIssueConverter = toIssueConverter;
    }

    private void insertAdvertisingForIssue(final Integer issueId, final List<String> filePath) {
        if (filePath == null) {
            return;
        }
        for (String path : filePath) {
            advertisingDao.insertAdvertising(new Advertising(issueId, path));
        }
    }

    private void insertArticlesForIssue(final Integer issueId, final List<Integer> articlesId) {
        for (Integer articleId : articlesId) {
            issueArticleDao.insertIssueArticle(new IssueArticle(articleId, issueId));
        }
    }

    @Override
    public List<Issue> getListOfAllIssues() {
        return issueDao.getListOfAllIssues();
    }

    @Override
    public IssueView getIssueViewByIssueId(final int issueId) {
        Issue issue = issueDao.getIssueById(issueId);
        return toIssueViewConverter.convert(issue);
    }

    @Override
    public void deleteIssueById(final int id) {
        issueArticleDao.deleteIssueArticleByIssueId(id);
        advertisingDao.deleteAdvertisingByIssueId(id);
        issueDao.deleteIssueById(id);
    }

    @Override
    public List<IssueInfo> getIssueListByPublishingId(final int publishingId) {
        List<Issue> issues = issueDao.getIssueListByPublishingId(publishingId);
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issues) {
            issueInfoList.add(toIssueInfoConverter.convert(issue));
        }
        return issueInfoList;
    }

    @Override
    public List<Integer> getArticleIdList(final int issueId) {
        List<IssueArticle> issueArticleList =
            issueArticleDao.getIssueArticleListByIssueId(issueId);
        List<Integer> articleIdList = new ArrayList<>();
        for (IssueArticle ia : issueArticleList) {
            articleIdList.add(ia.getArticleId());
        }
        return articleIdList;
    }

    @Override
    public List<String> getAdvertisingFilePath(final int issueId) {
        List<Advertising> advertising =
            advertisingDao.getAdvertisingListByIssueId(issueId);
        List<String> filePaths = new ArrayList<>();
        for (Advertising a : advertising) {
            filePaths.add(a.getFilePath());
        }
        return filePaths;
    }

    @Override
    public List<Issue> getIssuesByDate(final LocalDate date) {
        return issueDao.getIssueByDate(date);
    }

    @Override
    public void createNewIssue(final IssueForm issueForm) {
        Issue issue = toIssueConverter.convert(issueForm);
        Integer issueId = issueDao.insertIssue(issue);
        insertAdvertisingForIssue(issueId, issueForm.getAdvertisingPath());
        insertArticlesForIssue(issueId, issueForm.getArticlesId());
    }

    @Override
    public void updateIssue(final Integer issueId, final IssueForm issueForm) {
        Issue issue = toIssueConverter.convert(issueForm);
        issue.setId(issueId);
        issueDao.updateIssue(issue);
        advertisingDao.deleteAdvertisingByIssueId(issueId);
        insertAdvertisingForIssue(issueId, issueForm.getAdvertisingPath());
        issueArticleDao.deleteIssueArticleByIssueId(issueId);
        insertArticlesForIssue(issueId, issueForm.getArticlesId());
    }

    @Override
    public Issue getIssueById(final int issueId) {
        return issueDao.getIssueById(issueId);
    }


    @Override
    public boolean isArticlePublished(final int articleId) {
        return issueDao.isArticlePublished(articleId);
    }

    @Override
    public void deleteIssueArticleByArticleId(final int articleId) {
        issueDao.deleteIssueArticleByArticleId(articleId);
    }
}
