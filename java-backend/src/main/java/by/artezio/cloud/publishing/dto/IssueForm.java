package by.artezio.cloud.publishing.dto;

import org.springframework.stereotype.Component;

@Component
public class IssueForm {

    private int publishingId;

    public IssueForm() {

    }

    public int getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(int publishingId) {
        this.publishingId = publishingId;
    }
}
