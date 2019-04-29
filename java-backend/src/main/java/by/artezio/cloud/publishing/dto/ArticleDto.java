package by.artezio.cloud.publishing.dto;

/**
 * DTO для передачи через REST списка неопубликованных статей для сервиса с номерами.
 *
 * @author Denis Shubin
 */
public class ArticleDto {

    private Integer id;
    private Integer publishingId;
    private Integer topicId;
    private String title;
    private String content;
    private Integer authorId;

    /**
     * Возвращает id статьи.
     *
     * @return int id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id в указанное значение.
     *
     * @param id int.
     */
    public void setId(final Integer id) {
        this.id = id;
    }


    /**
     * Возвращает название статьи.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название статьи.
     *
     * @param title String
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Возвращает содержимое статьи.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает содержимое статьи.
     *
     * @param content String
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Возвращает журнал/газету статьи.
     *
     * @return id журнала
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает журнал/газету статьи.
     *
     * @param publishingId id журнала
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает рубрику статьи.
     *
     * @return id рубрики
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * Устанавливает рубрику статьи.
     *
     * @param topicId id рубрики
     */
    public void setTopicId(final Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * Возвращает id автора статьи.
     *
     * @return id автора
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * Устанавливает автора статьи.
     *
     * @param authorId id автора
     */
    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

}
