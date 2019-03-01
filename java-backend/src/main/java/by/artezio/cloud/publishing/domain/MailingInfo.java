package by.artezio.cloud.publishing.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author vgamezo
 */
public class MailingInfo {

    private int mailingId;
    private int publishingId;
    //TODO must be private String publishingName;
    private int issueId;
    //TODO must be private String issueNumber;
    private LocalDateTime dateTime;
    private String result;

    public MailingInfo() {
    }

    public MailingInfo(int mailingId, int publishingId, int issueId, LocalDateTime dateTime, String result) {
        this.mailingId = mailingId;
        this.publishingId = publishingId;
        this.issueId = issueId;
        this.dateTime = dateTime;
        this.result = result;
    }

    public Date getDate() {
        return Timestamp.valueOf(dateTime);
    }

    public int getMailingId() {
        return mailingId;
    }

    public void setMailingId(int mailingId) {
        this.mailingId = mailingId;
    }

    public int getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(int publishingId) {
        this.publishingId = publishingId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
