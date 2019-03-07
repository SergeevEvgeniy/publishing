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
public class MailingService {

    private MailingDao mailingDao;
    private PublishingDao publishingDao;

    /**
     * Конструктор с параметрами.
     * @param mailingDao mailingDao
     * @param publishingDao publishingDao
     */
    public MailingService(final MailingDao mailingDao, final PublishingDao publishingDao) {
        this.mailingDao = mailingDao;
        this.publishingDao = publishingDao;
    }

    /**
     * Метод, возвращающий список всех рассылок.
     * @return список рассылок
     */
    public List<MailingInfo> getAllMailingInfo() {
        return mailingDao.getAllMailingInfo();
    }

    /**
     * Возвращает список всех публикаций {@link Publishing}, доступных издательству.
     * @return список объектов {@link Publishing}
     */
    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    /**
     * Возвращает список email-адресов, которые принимали участие в последней рассылке публикации с id == {@param id}.
     * @param id id публикации
     * @return Список email-адресов
     */
    public List<String> getEmailListByPublishingId(final int id) {
        return mailingDao.getEmailListByPublishingId(id);
    }

    /**
     * Метод, обновляющий список подписчиков на издание с <code>id == {@param publishingId}</code>.
     * @param publishingId id издания.
     * @param emails новый список подписчиков.
     * @return <code>true</code>, если обновление прошло успешно, иначе <code>false</code>.
     */
    public boolean updateSubscribersListByPublishingId(final int publishingId, final List<String> emails) {
        boolean wasSuccessUpdated = true;

        Integer mailingId = mailingDao.getMailingIdByPublishingId(publishingId);
        if (mailingId == null) {
            mailingId = mailingDao.createNewMailingByPublishingId(publishingId);
        }

        for (String email : emails) {
            wasSuccessUpdated &= mailingDao.addNewSubscriberByMailingId(mailingId, email);
        }

        return wasSuccessUpdated;
    }
}
