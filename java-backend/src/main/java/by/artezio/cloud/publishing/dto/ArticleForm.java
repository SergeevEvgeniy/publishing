package by.artezio.cloud.publishing.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Класс, содержащий детальную информацию о статье.
 *
 * <p>Используется для отображения статей на странице <code>updateArticle.jsp</code>.
 *
 * @author Denis Shubin
 */
public class ArticleForm {

    private Integer authorId;
    private Integer publishingId;
    private Integer topicId;

    @NotNull
    private String title;

    @Size(min = 50)
    private String content;
    private List<Integer> coauthors;

    /**
     * @return id автора статьи
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId id автора статьи
     */
    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

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
     * @return название статьи
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title название статьи
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return содержание статьи
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content содержание статьи
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @return список id соавторов
     */
    public List<Integer> getCoauthors() {
        return coauthors;
    }

    /**
     * @param coauthors список id соавторов
     */
    public void setCoauthors(final List<Integer> coauthors) {
        this.coauthors = coauthors;
    }
}
