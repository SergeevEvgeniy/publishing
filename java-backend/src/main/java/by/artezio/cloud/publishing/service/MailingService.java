package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dto.MailingInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для сервисов, реализующих логику взаимодействия с данными рассылок.
 *
 * @author vgamezo
 */
public interface MailingService {

    /**
     * Возвращает список всех рассылок.
     *
     * @return список рассылок
     */
    List<MailingInfo> getAllMailingInfo();

    /**
     * Возвращает список email-адресов,
     * которые принимали участие в последней рассылке публикации с <code>id == publishingId</code>.
     *
     * @param publishingId id публикации
     * @return Список email-адресов
     */
    List<String> getEmailList(int publishingId);

    /**
     * Обновляет список email-адресов на издание с <code>id == publishingId</code>.
     *
     * @param publishingId id издания.
     * @param emails новый список email-адресов.
     * @return <code>true</code>, если обновление прошло успешно, иначе <code>false</code>.
     */
    boolean updateEmailList(int publishingId, List<String> emails);

    /**
     * Делает рассылку всех номеров всех изданий, у которых время издания равно localDateTime.
     *
     * @param localDateTime время, на момент которого происходит рассылка.
     *                      К результатам рассылок добавляется рассылки, время публикации которых есть localDateTime.
     */
    void sendMail(LocalDateTime localDateTime);
}
