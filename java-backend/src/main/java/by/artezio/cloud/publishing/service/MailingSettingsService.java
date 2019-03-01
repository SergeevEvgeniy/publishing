package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.MailingDao;
import by.artezio.cloud.publishing.dao.PublishingDao;
import by.artezio.cloud.publishing.domain.Publishing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для удовлетворения потребностей, связанных со страницей настройки рассылки.
 * @author vgamezo
 */
@Service
public class MailingSettingsService {

    @Autowired
    private PublishingDao publishingDao;

    @Autowired
    private MailingDao mailingDao;

    /**
     * Возвращает список всех публикаций {@link Publishing}, доступных издательству.
     * @return список объектов {@link Publishing}
     */
    public List<Publishing> getPublishingList() {
        return publishingDao.getPublishingList();
    }

    /**
     * Возвращает список email-адресов, которые принимали участие в последней рассылке публикации с id == {@param is}.
     * @param id id публикации
     * @return Список email-адресов
     */
    public List<String> getEmailListByPublishingId(final int id) {
        return mailingDao.getEmailListByPublishingId(id);
    }
}
