package by.artezio.cloud.publishing.domain;

import java.util.Objects;

public class Article {

    private int id;
    private int publishingId;
    private int topicId;
    private String title;
    private String content;
    private int authorId;

    public Article() {
    }

    public Article(int id, int publishingId, int topicId, String title, String content, int authorId) {
        this.id = id;
        this.publishingId = publishingId;
        this.topicId = topicId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
            publishingId == article.publishingId &&
            topicId == article.topicId &&
            authorId == article.authorId &&
            Objects.equals(title, article.title) &&
            Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishingId, topicId, title, content, authorId);
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", publishingId=" + publishingId +
            ", topicId=" + topicId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", authorId=" + authorId +
            '}';
    }
}
