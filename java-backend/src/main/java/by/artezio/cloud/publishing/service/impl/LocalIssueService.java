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
import by.artezio.cloud.publishing.service.converter.IssueToIssueFormConverter;
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

    private IssueToIssueViewConverter issueToIssueViewConverter;

    private IssueToIssueInfoConverter issueToIssueInfoConverter;

    private IssueFormToIssueConverter issueFormToIssueConverter;

    private IssueToIssueFormConverter issueToIssueFormConverter;

    /**
     * Конструктор с параметрами.
     *
     * @param issueDao             {@link IssueDao}
     * @param issueArticleDao      {@link IssueArticleDao}
     * @param advertisingDao       {@link AdvertisingDao}
     * @param issueFormToIssueConverter     {@link IssueFormToIssueConverter}
     * @param issueToIssueInfoConverter {@link IssueToIssueInfoConverter}
     * @param issueToIssueViewConverter {@link IssueToIssueViewConverter}
     * @param issueToIssueFormConverter {@link IssueToIssueFormConverter}
     */
    public LocalIssueService(final IssueDao issueDao,
                             final IssueArticleDao issueArticleDao,
                             final AdvertisingDao advertisingDao,
                             final IssueToIssueInfoConverter issueToIssueInfoConverter,
                             final IssueToIssueViewConverter issueToIssueViewConverter,
                             final IssueFormToIssueConverter issueFormToIssueConverter,
                             final IssueToIssueFormConverter issueToIssueFormConverter) {
        this.issueDao = issueDao;
        this.issueArticleDao = issueArticleDao;
        this.advertisingDao = advertisingDao;
        this.issueToIssueInfoConverter = issueToIssueInfoConverter;
        this.issueToIssueViewConverter = issueToIssueViewConverter;
        this.issueFormToIssueConverter = issueFormToIssueConverter;
        this.issueToIssueFormConverter = issueToIssueFormConverter;
    }

    private List<String> getAdvertisingFilePath(final int issueId) {
        List<Advertising> advertising =
            advertisingDao.getAdvertisingListByIssueId(issueId);
        List<String> filePaths = new ArrayList<>();
        for (Advertising a : advertising) {
            filePaths.add(a.getFilePath());
        }
        return filePaths;
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
    public IssueView getIssueViewByIssueId(final int issueId) {
        IssueView issueView = issueToIssueViewConverter.convert(issueDao.getIssueById(issueId));
        issueView.setAdvertisingPath(getAdvertisingFilePath(issueId));
        return issueView;
    }

    @Override
    public IssueForm getIssueFormByIssueId(final int issueId) {
        IssueForm issueForm = issueToIssueFormConverter.convert(issueDao.getIssueById(issueId));
        issueForm.setAdvertisingPath(getAdvertisingFilePath(issueId));
        return issueForm;
    }

    @Override
    public Issue deleteIssueById(final int id) {
        Issue issue = issueDao.getIssueById(id);
        issueArticleDao.deleteIssueArticleByIssueId(id);
        advertisingDao.deleteAdvertisingByIssueId(id);
        issueDao.deleteIssueById(id);
        return issue;
    }

    @Override
    public List<IssueInfo> getIssueListByPublishingId(final int publishingId) {
        List<Issue> issues = issueDao.getIssueListByPublishingId(publishingId);
        List<IssueInfo> issueInfoList = new ArrayList<>();
        for (Issue issue : issues) {
            issueInfoList.add(issueToIssueInfoConverter.convert(issue));
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
    public List<Issue> getIssuesByDate(final LocalDate date) {
        return issueDao.getIssueByDate(date);
    }

    @Override
    public Integer createNewIssue(final IssueForm issueForm) {
        Issue issue = issueFormToIssueConverter.convert(issueForm);
        Integer issueId = issueDao.insertIssue(issue);
        insertAdvertisingForIssue(issueId, issueForm.getAdvertisingPath());
        insertArticlesForIssue(issueId, issueForm.getArticlesId());
        return issueId;
    }

    @Override
    public void updateIssue(final Integer issueId, final IssueForm issueForm) {
        Issue issue = issueFormToIssueConverter.convert(issueForm);
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
