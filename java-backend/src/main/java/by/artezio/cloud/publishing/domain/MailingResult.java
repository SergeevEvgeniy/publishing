package by.artezio.cloud.publishing.domain;

import java.time.LocalDateTime;

/**
 * Класс-сущность для результатов рассылки.
 * @author vgamezo
 */
public class MailingResult {

    private Integer mailingId;
    private Integer issueId;
    private LocalDateTime dateTime;
    private String result;

    /**
     * Конструктор с параметрами.
     * @param mailingId id рассылки
     * @param issueId id номера для рассылки
     * @param dateTime дата рассылки
     * @param result результат рассылки
     */
    public MailingResult(final Integer mailingId, final Integer issueId, final LocalDateTime dateTime, final String result) {
        this.mailingId = mailingId;
        this.issueId = issueId;
        this.dateTime = dateTime;
        this.result = result;
    }

    /**
     * Возвращает id рассылки.
     * @return id рассылки
     */
    public Integer getMailingId() {
        return mailingId;
    }

    /**
     * Устанавливает id рассылки.
     * @param mailingId id рассылки
     */
    public void setMailingId(final Integer mailingId) {
        this.mailingId = mailingId;
    }

    /**
     * Возвращает id номера, который был задействован в рассылке.
     * @return id номера
     */
    public Integer getIssueId() {
        return issueId;
    }

    /**
     * Устанавливает id номера, который был задействован в рассылке.
     * @param issueId id номера
     */
    public void setIssueId(final Integer issueId) {
        this.issueId = issueId;
    }

    /**
     * Возвращает дату совершения рассылки.
     * @return дата рассылки
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Устанавливает дату совершения рассылки.
     * @param dateTime дата рассылки
     */
    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Возвращает результат рассылки.
     * @return результат рассылки в текстовом виде
     */
    public String getResult() {
        return result;
    }

    /**
     * Устанавливает результат совершенной рассылки.
     * @param result результат рассылки в текстовом виде
     */
    public void setResult(final String result) {
        this.result = result;
    }
}
