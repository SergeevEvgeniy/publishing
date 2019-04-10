package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.domain.Mailing;
import by.artezio.cloud.publishing.domain.MailingResult;
import by.artezio.cloud.publishing.domain.MailingSubscriber;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.MailSender;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Конкретная реализация интерфейса {@link MailingService}, построенная на конкретном обращении к локальной DAO.
 *
 * @author vgamezo
 */
@Service
public class MailingServiceImpl implements MailingService {

    private MailingDao mailingDao;
    private MailSender mailSender;
    private IssueService issueService;
    private PublishingService publishingService;

    /**
     * Конструктор с параметрами.
     * @param mailingDao dao для взаимодействия с БД.
     * @param mailSender рассыльщик писем.
     * @param issueService сервис для номеров.
     * @param publishingService сервис для изданий.
     */
    public MailingServiceImpl(final MailingDao mailingDao,
                              final MailSender mailSender,
                              final IssueService issueService,
                              final PublishingService publishingService) {
        this.mailingDao = mailingDao;
        this.mailSender = mailSender;
        this.issueService = issueService;
        this.publishingService = publishingService;
    }

    @Override
    public List<String> getEmailList(final int publishingId) {
        return mailingDao.getEmailList(publishingId);
    }

    @Override
    public boolean updateEmailList(final int mailingId, final List<String> emails) {
        mailingDao.clearMailingSubscribersByMailingId(mailingId);

        boolean wasSuccessUpdated = true;
        if (emails != null) {
            for (String email : emails) {
                MailingSubscriber subscriber = new MailingSubscriber();
                subscriber.setMailingId(mailingId);
                subscriber.setEmail(email);
                wasSuccessUpdated = mailingDao.addMailingSubscriber(subscriber) & wasSuccessUpdated;
            }
        }

        return wasSuccessUpdated;
    }

    @Override
    public Integer getMailingIdByPublishingId(final int publishingId) {
        return mailingDao.getMailingIdByPublishingId(publishingId);
    }

    @Override
    public Integer createMailingRecord(final int publishingId) {
        return mailingDao.createMailingRecord(publishingId);
    }

    @Override
    public boolean addMailingResult(final MailingResult mailingResult) {
        return mailingDao.addMailingResult(mailingResult);
    }

    @Override
    public List<Mailing> getAllMailing() {
        return mailingDao.getAllMailing();
    }

    @Override
    public List<MailingResult> getAllMailingResult() {
        return mailingDao.getAllMailingResult();
    }

    @Override
    public void sendMail(final LocalDateTime localDateTime) {
        List<Issue> issues = this.issueService.getIssuesByDate(localDateTime.toLocalDate());
        for (Issue issue : issues) {
            int publishingId = issue.getPublishingId();
            int mailingId = this.getMailingIdByPublishingId(publishingId);
            String publishingTitle = this.publishingService.getPublishingTitle(publishingId);
            List<String> subscribers = this.getEmailList(publishingId);
            List<String> results = this.mailSender.sendMail(
                subscribers,
                "!!! Свежий выпуск \"" + issue.getNumber() + "\"!!!",
                "Вышел свежий номер издательства \"" + publishingTitle + "\""
            );

            this.addMailingResult(
                new MailingResult(
                    mailingId,
                    issue.getId(),
                    localDateTime,
                    String.join("\n", results)
                )
            );
        }
    }
}
