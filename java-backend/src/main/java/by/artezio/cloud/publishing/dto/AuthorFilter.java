package by.artezio.cloud.publishing.dto;

/**
 * Класс, использующийся в REST сервисе для выбора авторов по указанным полям.
 */
public class AuthorFilter {

    private Integer publishingId;
    private Integer topicId;
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
     * @return {@code true}, если нужны авторы, у которых есть хотя бы одна опубликованная статья.
     * {@code false}, если нужны авторы, у которых есть хотя бы одна неопубликованная статья.
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * @param published {@code true}, если нужны авторы, у которых есть хотя бы одна опубликованная статья.
     *                  {@code false}, если нужны авторы, у которых есть хотя бы одна неопубликованная статья.
     */
    public void setPublished(final Boolean published) {
        this.published = published;
    }
}
