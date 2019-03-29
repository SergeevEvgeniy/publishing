package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.MailingResultType;

import java.util.Date;

/**
 * Класс-сущность для удобного вывода информации о рассылках на страницу jsp.
 * @author vgamezo
 */
public class MailingInfo {

    private int mailingId;
    private int publishingId;
    //TODO must be private String publishingName;
    private int issueId;
    //TODO must be private String issueNumber;
    private Date date;
    private String result;

    /**
     * Конструктор по умолчанию.
     */
    public MailingInfo() {
    }

    /**
     * Конструктор с параметрами.
     * @param mailingId -- id рассылки.
     * @param publishingId -- id издания, по которому была сделана рассылка.
     * @param issueId -- id номера, который пошел в рассылку.
     * @param date -- Время, когда была сделана рассылка.
     * @param result -- Результат рассылки.
     */
    public MailingInfo(final int mailingId, final int publishingId, final int issueId,
                       final Date date, final String result) {
        this.mailingId = mailingId;
        this.publishingId = publishingId;
        this.issueId = issueId;
        this.date = date;
        this.result = result;
    }

    /**
     * Возвращает id рассылки.
     * @return id рассылки.
     */
    public int getMailingId() {
        return mailingId;
    }

    /**
     * Устанавливает id рассылки.
     * @param mailingId id рассылки.
     */
    public void setMailingId(final int mailingId) {
        this.mailingId = mailingId;
    }

    /**
     * Возвращает id издания, по которому была сделана рассылка.
     * @return id издания, по которому была сделана рассылка.
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id издания, по которому была сделана рассылка.
     * @param publishingId id издания, по которому была сделана рассылка.
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает id номера, который пошел в рассылку.
     * @return id номера, который пошел в рассылку.
     */
    public int getIssueId() {
        return issueId;
    }

    /**
     * Устанавливает id номера, на который была сделана рассылка.
     * @param issueId id номера, на который была сделана рассылка.
     */
    public void setIssueId(final int issueId) {
        this.issueId = issueId;
    }

    /**
     * Возвращает дату, когда была сделана рассылка.
     * @return дата, когда была сделана рассылка.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Устанавливает дату сделанной рассылки.
     * @param date дата сделанной рассылки.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Возвращает результат сделанной рассылки.
     * @return результат сделанной рассылки.
     */
    public String getResult() {
        return result;
    }

    /**
     * Устанавливает результат рассылки.
     * @param result результат рассылки в текстовом виде.
     */
    public void setResult(final String result) {
        this.result = result;
    }

    /**
     * Проверяет идентичность сообщения message и значения в константе MailingResultType.SUCCESS.
     *
     * @return <code>true</code>, если message соответствует значению в константе MailingResultType.SUCCESS,
     *         <code>false</code>, иначе.
     */
    public boolean isSuccess() {
        return MailingResultType.SUCCESS.toString().compareTo(this.result.trim()) == 0;
    }
}
