package by.artezio.cloud.publishing.domain;

import java.util.Objects;

/**
 * Класс-сущность, представляет строку из таблицы"Articles".
 *
 * @author Denis Shubin.
 */
public class Article {

    private int id;
    private int publishingId;
    private int topicId;
    private String title;
    private String content;
    private int authorId;

    /**
     * Конструктор, создаёт класс с стандартными значениями полей.
     */
    public Article() {
    }

    /**
     * Конструктор для создания сущности с указанными значениями полей.
     *
     * @param id           - id статьи.
     * @param publishingId - id журнала.
     * @param topicId      - id рубрики.
     * @param title        - название статьи.
     * @param content      - содержимое статьи.
     * @param authorId     - id автора.
     */
    public Article(final int id, final int publishingId, final int topicId, final String title,
                   final String content, final int authorId) {
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
    public int getId() {
        return id;
    }

    /**
     * Устанавливает id в указанное значение.
     *
     * @param id int.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Возвращает id журнала, в котором опубликована статья.
     *
     * @return int
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает значение publishingId.
     *
     * @param publishingId int
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает id рубрики.
     *
     * @return int
     */
    public int getTopicId() {
        return topicId;
    }

    /**
     * Устанавливает значение topicId.
     *
     * @param topicId int
     */
    public void setTopicId(final int topicId) {
        this.topicId = topicId;
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
     * Возвращает id автора статьи.
     *
     * @return int
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Устанавливает id автора статьи.
     *
     * @param authorId int
     */
    public void setAuthorId(final int authorId) {
        this.authorId = authorId;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        return id == article.id
            && publishingId == article.publishingId
            && topicId == article.topicId
            && authorId == article.authorId
            && Objects.equals(title, article.title)
            && Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishingId, topicId, title, content, authorId);
    }

    @Override
    public String toString() {
        return "Article{"
            + "id="
            + id
            + ", publishingId="
            + publishingId
            + ", topicId="
            + topicId
            + ", title='"
            + title
            + '\''
            + ", content='"
            + content
            + '\''
            + ", authorId="
            + authorId
            + '}';
    }
}
