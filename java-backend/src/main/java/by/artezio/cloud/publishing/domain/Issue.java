package by.artezio.cloud.publishing.domain;

import java.time.LocalDate;

public class Issue {

    private int id;
    private int publishingId;
    private String number;
    private LocalDate date;
    private boolean published;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(int publishingId) {
        this.publishingId = publishingId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
