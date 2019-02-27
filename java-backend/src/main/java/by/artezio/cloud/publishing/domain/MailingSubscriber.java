package by.artezio.cloud.publishing.domain;

public class MailingSubscriber {

    private int mailingId;
    private String email;

    public int getMailingId() {
        return mailingId;
    }

    public void setMailingId(int mailingId) {
        this.mailingId = mailingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
