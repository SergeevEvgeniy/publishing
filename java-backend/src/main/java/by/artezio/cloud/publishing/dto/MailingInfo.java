package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.MailingResultType;

import java.util.Date;

/**
 * Класс-сущность для удобного вывода информации о рассылках на страницу jsp.
 * @author vgamezo
 */
public class MailingInfo {

    private int mailingId;
    private String publishingTitle;
    private String issueNumber;
    private Date date;
    private String result;

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
    public String getPublishingTitle() {
        return publishingTitle;
    }

    /**
     * Устанавливает id издания, по которому была сделана рассылка.
     * @param publishingTitle id издания, по которому была сделана рассылка.
     */
    public void setPublishingTitle(final String publishingTitle) {
        this.publishingTitle = publishingTitle;
    }

    /**
     * Возвращает id номера, который пошел в рассылку.
     * @return id номера, который пошел в рассылку.
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * Устанавливает id номера, на который была сделана рассылка.
     * @param issueNumber id номера, на который была сделана рассылка.
     */
    public void setIssueNumber(final String issueNumber) {
        this.issueNumber = issueNumber;
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
        return MailingResultType.SUCCESS.getMessage().equals(this.result);
    }
}
