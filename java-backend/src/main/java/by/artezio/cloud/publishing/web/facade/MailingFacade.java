package by.artezio.cloud.publishing.web.facade;

import by.artezio.cloud.publishing.domain.Mailing;
import by.artezio.cloud.publishing.domain.MailingResult;
import by.artezio.cloud.publishing.dto.MailingInfo;
import by.artezio.cloud.publishing.service.IssueService;
import by.artezio.cloud.publishing.service.MailingService;
import by.artezio.cloud.publishing.service.PublishingService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

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

    private Converter<MailingResult, MailingInfo> mailingInfoConverter;

    /**
     * Конструктор с параметрами. Нужен для инъекции зависимостей.
     * @param issueService сервис для связи с нмоерами.
     * @param mailingService сервис для связи с рассылками.
     * @param publishingService сервис для связи с изданиями.
     * @param mailingInfoConverter конвертер из MailingResult в MailingInfo.
     */
    public MailingFacade(final IssueService issueService,
                         final MailingService mailingService,
                         final PublishingService publishingService,
                         final Converter<MailingResult, MailingInfo> mailingInfoConverter) {
        this.issueService = issueService;
        this.mailingService = mailingService;
        this.publishingService = publishingService;
        this.mailingInfoConverter = mailingInfoConverter;
    }

    /**
     * Возвращает список всех рассылок.
     *
     * @return список рассылок
     */
    public List<MailingInfo> getAllMailingInfo() {
        List<Mailing> mailingList = this.mailingService.getAllMailing();
        List<MailingResult> mailingResults = this.mailingService.getAllMailingResult();

        Map<Integer, Integer> mailingMap = new LinkedHashMap<>(mailingList.size());
        for (Mailing mailing : mailingList) {
            mailingMap.put(mailing.getId(), mailing.getPublishingId());
        }

        List<MailingInfo> mailingInfos = new ArrayList<>(mailingResults.size());

        for (MailingResult mailingResult : mailingResults) {
            String publishingTitle = this.publishingService.getPublishingTitle(mailingMap.get(mailingResult.getMailingId()));
            String issueNumber = issueService.getIssueById(mailingResult.getIssueId()).getNumber();

            MailingInfo info = this.mailingInfoConverter.convert(mailingResult);

            info.setPublishingTitle(publishingTitle);
            info.setIssueNumber(issueNumber);

            mailingInfos.add(info);
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
        Integer mailingId = this.mailingService.getMailingIdByPublishingId(publishingId);
        if (mailingId == null) {
            mailingId = this.mailingService.createMailingRecord(publishingId);
        }

        return this.mailingService.updateEmailList(mailingId, emails);
    }
}
