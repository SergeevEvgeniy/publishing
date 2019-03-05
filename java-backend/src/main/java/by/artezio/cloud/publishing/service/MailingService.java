package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.domain.Publishing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, реализующий логику получения данных для {@link by.artezio.cloud.publishing.web.controllers.MailingController}.
 * @author vgamezo
 */
@Service
public class MailingService {

    @Autowired
    private MailingDao mailingDao;

    @Autowired
    private PublishingDao publishingDao;

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
}
