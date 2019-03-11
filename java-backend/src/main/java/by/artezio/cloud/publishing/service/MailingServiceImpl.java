package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.domain.Publishing;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, реализующий логику получения данных для {@link by.artezio.cloud.publishing.web.controllers.MailingController}.
 * @author vgamezo
 */
@Service
public class MailingServiceImpl implements MailingService {

    private MailingDao mailingDao;
    private PublishingDao publishingDao;

    /**
     * Конструктор с параметрами.
     * @param mailingDao mailingDao
     * @param publishingDao publishingDao
     */
    public MailingServiceImpl(final MailingDao mailingDao, final PublishingDao publishingDao) {
        this.mailingDao = mailingDao;
        this.publishingDao = publishingDao;
    }

    @Override
    public List<MailingInfo> getAllMailingInfo() {
        return mailingDao.getAllMailingInfo();
    }

    @Override
    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
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
}
