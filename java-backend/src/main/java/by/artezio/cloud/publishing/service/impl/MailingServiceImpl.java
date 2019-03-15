package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.service.MailSender;
import by.artezio.cloud.publishing.service.MailingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    /**
     * Конструктор с параметрами.
     * @param mailingDao dao для взаимодействия с БД.
     * @param mailSender отправитель писем.
     */
    public MailingServiceImpl(final MailingDao mailingDao, final MailSender mailSender) {
        this.mailingDao = mailingDao;
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
        mailSender.sendMail(Arrays.asList("mail@mail.ru"), "AutoMailing", dateTime.toString() + " Hi. This is AutoMailing");
        //TODO add mailing_result
    }
}
