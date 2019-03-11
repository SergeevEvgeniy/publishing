package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.service.MailingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Конкретная реализация интерфейса {@link MailingService}, построенная на конкретном обращении к локальной DAO.
 *
 * @author vgamezo
 */
@Service
public class MailingServiceImpl implements MailingService {

    private MailingDao mailingDao;
    private PublishingDao publishingDao; //TODO вынести в PublishingService.

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
