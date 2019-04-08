package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Mailing;
import by.artezio.cloud.publishing.domain.MailingResult;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.MailSender;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vgamezo
 */
@Service
public class MailingFacade {

    private IssueService issueService;
    private MailingService mailingService;
    private PublishingService publishingService;
    private MailSender mailSender;

    /**
     * Конструктор с параметрами. Нужен для инъекции зависимостей.
     * @param issueService сервис для связи с нмоерами.
     * @param mailingService сервис для связи с рассылками.
     * @param publishingService сервис для связи с изданиями.
     * @param mailSender рассыльщик писем.
     */
    public MailingFacade(final IssueService issueService,
                         final MailingService mailingService,
                         final PublishingService publishingService,
                         final MailSender mailSender) {
        this.issueService = issueService;
        this.mailingService = mailingService;
        this.publishingService = publishingService;
        this.mailSender = mailSender;
    }

    /**
     * Возвращает список всех рассылок.
     *
     * @return список рассылок
     */
    public List<MailingInfo> getAllMailingInfo() {
        Map<Integer, Integer> mailingMap = new LinkedHashMap<>();
        List<Mailing> mailingList = mailingService.getAllMailing();
        for (Mailing mailing : mailingList) {
            mailingMap.put(mailing.getId(), mailing.getPublishingId());
        }

        List<MailingResult> mailingResults = mailingService.getAllMailingResult();
        List<MailingInfo> mailingInfos = new ArrayList<>();

        for (MailingResult mailingResult : mailingResults) {
            Integer mailingId = mailingResult.getMailingId();
            String publishingTitle = this.publishingService.getPublishingTitle(mailingMap.get(mailingId));
            mailingInfos.add(new MailingInfo(
                mailingId,
                publishingTitle,
                issueService.getIssueById(mailingResult.getIssueId()).getNumber(),
                Timestamp.valueOf(mailingResult.getDateTime()),
                mailingResult.getResult())
            );
        }
        return mailingInfos;
    }

    /**
     * Возвращает список email-адресов, на которые будет происходить рассылка номеров издания с <code>id == publishingId</code>.
     *
     * @param publishingId id издания
     * @return Список email-адресов
     */
    public List<String> getEmailList(final int publishingId) {
        return mailingService.getEmailList(publishingId);
    }

    /**
     * Обновляет список email-адресов на издание с <code>id == publishingId</code>.
     * В случае, если не было найдено ни одной рассылки, связанной с этим изданием, происходит создание новой рассылки,
     *  а затем происходит обновление списка подписчиков, по найденному id рассылки.
     *
     * @param publishingId id издания.
     * @param emails новый список email-адресов.
     * @return <code>true</code>, если обновление прошло успешно, иначе <code>false</code>.
     */
    public boolean updateEmailList(final int publishingId, final List<String> emails) {
        Integer mailingId = mailingService.getMailingIdByPublishingId(publishingId);
        if (mailingId == null) {
            mailingId = mailingService.createMailingRecord(publishingId);
        }

        return mailingService.updateEmailList(mailingId, emails);
    }
}
