package by.artezio.cloud.publishing.dto;

/**
 *
 */
public class ArticleFilter {

    private Integer publishingId;
    private Integer topicId;
    private Integer authorId;
    private Boolean published;

    /**
     * @return id журнала
     */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * @param publishingId id журнала
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * @return id рубрики
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * @param topicId id рубрики
     */
    public void setTopicId(final Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * @return id автора
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId id автора
     */
    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * @return {@code true}, если нужно найти неопубликованные статьи, иначе - {@code false}
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * @param published {@code true}, если нужно найти неопубликованные статьи, иначе - {@code false}
     */
    public void setPublished(final Boolean published) {
        this.published = published;
    }
}
