package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Review;

import java.util.List;

public class UpdateArticleDTO {

    private List<String> publishings;
    private List<String> topics;
    private String title;
    private String content;
    private List<String> currentCoauthors;
    private List<String> availableCoauthors;
    private List<Review> reviews;

    public List<String> getPublishings() {
        return publishings;
    }

    public void setPublishings(List<String> publishings) {
        this.publishings = publishings;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCurrentCoauthors() {
        return currentCoauthors;
    }

    public void setCurrentCoauthors(List<String> currentCoauthors) {
        this.currentCoauthors = currentCoauthors;
    }

    public List<String> getAvailableCoauthors() {
        return availableCoauthors;
    }

    public void setAvailableCoauthors(List<String> availableCoauthors) {
        this.availableCoauthors = availableCoauthors;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
