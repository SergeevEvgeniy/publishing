package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;

public class IssueForm {

    private int publishingId;

    private int articleId;

    private String number;

    private LocalDate localDate;

    public int getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(int publishingId) {
        this.publishingId = publishingId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

}
