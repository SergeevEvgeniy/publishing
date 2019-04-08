package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Mailing;
import by.artezio.cloud.publishing.domain.MailingResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс для сервисов, реализующих логику взаимодействия с данными рассылок.
 *
 * @author vgamezo
 */
public interface MailingService {

    /**
     * Возвращает список email-адресов, на которые будет происходить рассылка номеров издания с <code>id == publishingId</code>.
     *
     * @param publishingId id издания
     * @return Список email-адресов
     */
    List<String> getEmailList(int publishingId);

    /**
     * Обновляет список email-адресов на издание, к которому привызана рассылка с номером <code>id == mailingId</code>.
     *
     * @param mailingId id издания.
     * @param emails новый список email-адресов.
     * @return <code>true</code>, если обновление прошло успешно, иначе <code>false</code>.
     */
    boolean updateEmailList(int mailingId, List<String> emails);

    /**
     * Возвращает id рассылки, к которой привязано издание с id равным publishingId.
     * Если не было найдено ни одной рассылки, возвращает null
     * @param publishingId id издания.
     * @return Если id рассылки был найден, возвращает id рассылки, иначе возвращает <code>null</code>
     */
    Integer getMailingIdByPublishingId(int publishingId);

    /**
     * Создает новую рассылку, которая привязывается к изданию с id равным publishingId, и возвращает id этой рассылки.
     * @param publishingId id издания, на которое создается рассылка.
     * @return Id созданной рассылки.
     */
    Integer createMailingRecord(int publishingId);

    /**
     * Добавляет результат рассылки в базу данных. При удачном добавлении вызвращает <code>true</code>,
     * в случае возникновения какой-либо ошибки возвращает <code>false</code>.
     *
     * @param mailingId идентификатор рассылки.
     * @param issueId идентификатор номера, по которому происходила рассылка.
     * @param localDateTime день, на момент которого произошла рассылка.
     * @param result результат рассылки.
     */
    void addMailingResult(int mailingId, int issueId, LocalDateTime localDateTime, String result);

    /**

     * Возвращает список рассылок (будущих или уже произошедших).
     * @return список рассылок.
     */
    List<Mailing> getAllMailing();

    /**
     * Возвращает список результатов рассылок, произошедших за всё время, по всем изданиям.
     * @return список результатов рассылок.
     */
    List<MailingResult> getAllMailingResult();

    /**
     * Делает рассылку всех номеров всех изданий, у которых время издания равно localDateTime.
     *
     * @param localDateTime время, на момент которого происходит рассылка.
     *                      К результатам рассылок добавляется рассылки, время публикации которых есть localDateTime.
     */
    void sendMail(LocalDateTime localDateTime);
}
