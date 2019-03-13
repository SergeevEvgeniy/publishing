package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.service.MailSender;
import by.artezio.cloud.publishing.service.MailingService;
import org.springframework.stereotype.Service;

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
    public List<String> getEmailListByPublishingId(final int id) {
        return mailingDao.getEmailListByPublishingId(id);
    }

    @Override
    public boolean updateSubscribersListByPublishingId(final int publishingId, final List<String> emails) {
        boolean wasSuccessUpdated = true;

        Integer mailingId = mailingDao.getMailingIdByPublishingId(publishingId);
        if (mailingId == null) {
            mailingId = mailingDao.createNewMailingByPublishingId(publishingId);
        }

        mailingDao.clearMailingSubscribersByMailingId(mailingId);

        for (String email : emails) {
            wasSuccessUpdated &= mailingDao.addSubscriberByMailingId(mailingId, email);
        }

        return wasSuccessUpdated;
    }

    @Override
    public void sendMail() {
        mailSender.sendMail(Arrays.asList("mail@mail.ru"), "AutoMailing", "Hi. This is AutoMailing");
    }
}
