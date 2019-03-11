package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.dto.MailingInfo;

import java.util.List;

/**
 * Интерфейс для сервисов, реализующих логику преобразования данных из/в {@link by.artezio.cloud.publishing.dao.MailingDao}.
 * @author vgamezo
 */
public interface MailingService {

    /**
     * Метод, возвращающий список всех рассылок.
     * @return список рассылок
     */
    List<MailingInfo> getAllMailingInfo();

    /**
     * Возвращает список всех публикаций {@link Publishing}, доступных издательству.
     * @return список объектов {@link Publishing}
     */
    List<Publishing> getPublishingList();

    /**
     * Возвращает список email-адресов, которые принимали участие в последней рассылке публикации с id == {@param id}.
     * @param id id публикации
     * @return Список email-адресов
     */
    List<String> getEmailListByPublishingId(int id);

    /**
     * Метод, обновляющий список подписчиков на издание с <code>id == {@param publishingId}</code>.
     * @param publishingId id издания.
     * @param emails новый список подписчиков.
     * @return <code>true</code>, если обновление прошло успешно, иначе <code>false</code>.
     */
    boolean updateSubscribersListByPublishingId(int publishingId, List<String> emails);
}
