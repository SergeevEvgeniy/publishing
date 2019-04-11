package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляет строку из таблицы "Articles".
 *
 * @author Denis Shubin.
 */
public class Article {

    private Integer id;
    private Integer publishingId;
    private Integer topicId;
    private String title;
    private String content;
    private Integer authorId;

    /**
     * Конструктор, создаёт класс со стандартными значениями полей.
     */
    public Article() {
    }

    /**
     * Конструктор для создания объекта с указанными значениями полей.
     *
     * @param id           идентификатор статьи
     * @param publishingId id журнала/газеты, в котором печатается эта статья
     * @param topicId      id рубрики статьи
     * @param title        название статьи
     * @param content      текст статьи
     * @param authorId     id автора статьи
     */
    public Article(final Integer id, final Integer publishingId, final Integer topicId,
                   final String title, final String content, final Integer authorId) {
        this.id = id;
        this.publishingId = publishingId;
        this.topicId = topicId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

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
     * @return объект класса {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает журнал/газету статьи.
     *
     * @param publishingId объект класса {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает рубрику статьи.
     *
     * @return объект класса {@link Topic}
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * Устанавливает рубрику статьи.
     *
     * @param topicId объект класса {@link Topic}
     */
    public void setTopicId(final Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * Возвращает автора статьи.
     *
     * @return объект класса {@link Employee}
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * Устанавливает автора статьи.
     *
     * @param authorId объект класса {@link Employee}
     */
    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }
}
