package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;

import java.util.List;
import java.util.Map;

/**
 * DTO для передачи на страницу сведений о статье, необходимых для режима "просмотр".
 *
 * @author Denis Shubin
 */
public class ArticleView {

    private Integer articleId;
    private String publishingName;
    private String topicName;
    private String articleName;
    private String content;
    private List<Employee> coauthors;
    private Map<Employee, Review> reviews;

    /**
     * @return id статьи
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId id статьи
     */
    public void setArticleId(final Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * @return название журнала
     */
    public String getPublishingName() {
        return publishingName;
    }

    /**
     * @param publishingName название журнала
     */
    public void setPublishingName(final String publishingName) {
        this.publishingName = publishingName;
    }

    /**
     * @return название рубрики
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * @param topicName название рубрики
     */
    public void setTopicName(final String topicName) {
        this.topicName = topicName;
    }

    /**
     * @return название статьи
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     * @param articleName название статьи
     */
    public void setArticleName(final String articleName) {
        this.articleName = articleName;
    }

    /**
     * @return текст статьи
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content текст статьи
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @return список соавторов {@link Employee}
     */
    public List<Employee> getCoauthors() {
        return coauthors;
    }

    /**
     * @param coauthors список соавторов {@link Employee}
     */
    public void setCoauthors(final List<Employee> coauthors) {
        this.coauthors = coauthors;
    }

    /**
     * @return {@link Map} of {@link Employee}
     */
    public Map<Employee, Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews {@link Map} of {@link Employee}
     */
    public void setReviews(final Map<Employee, Review> reviews) {
        this.reviews = reviews;
    }
}
