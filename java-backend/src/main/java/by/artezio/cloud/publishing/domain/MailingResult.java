package by.artezio.cloud.publishing.domain;

import java.time.LocalDateTime;

/**
 * Класс-сущность для результатов рассылки.
 * @author vgamezo
 */
public class MailingResult {

    private int mailingId;
    private int issueId;
    private LocalDateTime date;
    private String result;

    /**
     * Возвращает id рассылки.
     * @return id рассылки
     */
    public int getMailingId() {
        return mailingId;
    }

    /**
     * Устанавливает id рассылки.
     * @param mailingId id рассылки
     */
    public void setMailingId(final int mailingId) {
        this.mailingId = mailingId;
    }

    /**
     * Возвращает id номера, который был задействован в рассылке.
     * @return id номера
     */
    public int getIssueId() {
        return issueId;
    }

    /**
     * Устанавливает id номера, который был задействован в рассылке.
     * @param issueId id номера
     */
    public void setIssueId(final int issueId) {
        this.issueId = issueId;
    }

    /**
     * Возвращает дату совершения рассылки.
     * @return дата рассылки
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Устанавливает дату совершения рассылки.
     * @param date дата рассылки
     */
    public void setDate(final LocalDateTime date) {
        this.date = date;
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
