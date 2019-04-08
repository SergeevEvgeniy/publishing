package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.domain.Issue;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.MailSender;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param issueService сервис для работы с номерами.
     * @param publishingService сервис для работы с издательствами.
     */
    public MailingServiceImpl(final MailingDao mailingDao,
                              final IssueService issueService,
                              final PublishingService publishingService) {
        this.mailingDao = mailingDao;
        this.issueService = issueService;
        this.publishingService = publishingService;
    }

    /**
     * Устанавливает отправителя писем.
     * @param mailSender отправитель писем.
     */
    @Autowired
    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public List<MailingInfo> getAllMailingInfo() {
        return mailingDao.getAllMailingInfo();
    }

    @Override
    public List<String> getEmailList(final int publishingId) {
        return mailingDao.getEmailList(publishingId);
    }

    @Override
    public boolean updateEmailList(final int publishingId, final List<String> emails) {
        boolean wasSuccessUpdated = true;

        Integer mailingId = mailingDao.getMailingIdByPublishingId(publishingId);
        if (mailingId == null) {
            mailingId = mailingDao.createMailingRecord(publishingId);
        }

        mailingDao.clearMailingSubscribersByMailingId(mailingId);

        if (emails != null) {
            for (String email : emails) {
                wasSuccessUpdated &= mailingDao.addEmailByMailingId(mailingId, email);
            }
        }

        return wasSuccessUpdated;
    }

    @Override
    public void sendMail(final LocalDateTime dateTime) {
        List<Issue> issues = issueService.getIssuesByDate(dateTime.toLocalDate());
        for (Issue issue : issues) {
            int publishingId = issue.getPublishingId();
            int mailingId = this.mailingDao.getMailingIdByPublishingId(publishingId);
            String publishingTitle = this.publishingService.getPublishingTitle(publishingId);
            List<String> subscribers = this.getEmailList(publishingId);
            List<String> results = this.mailSender.sendMail(
                subscribers,
                "!!! Свежий выпуск \"" + issue.getNumber() + "\"!!!",
                "Вышел свежий номер мздательства \"" + publishingTitle + "\""
            );

            this.mailingDao.addMailingResult(
                mailingId,
                issue.getId(),
                dateTime,
                String.join("\n", results)
            );
        }
    }
}
