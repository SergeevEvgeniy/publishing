package by.artezio.cloud.publishing.dto;

import java.util.List;

public class ArticleListDTO {
    private String title;
    private String publishing;
    private String topic;
    private String author;
    private List<String> coauthors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(List<String> coauthors) {
        this.coauthors = coauthors;
    }
}
