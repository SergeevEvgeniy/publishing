package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;

import java.util.List;
import java.util.Map;

/**
 * Класс, содержащий детальную информацию о статье.
 *
 * <p>Используется для отображения статей на странице <code>updateArticle.jsp</code>.
 *
 * @author Denis Shubin
 */
public class ArticleForm {

    private List<PublishingDTO> publishings;
    private List<Topic> topics;
    private String title;
    private String content;
    private List<Employee> currentCoauthors;
    private List<Employee> availableCoauthors;
    private Map<Employee, Review> reviews;

    /**
     * Возвращает список журналов/газет.
     *
     * @return List<PublishingDTO>
     */
    public List<PublishingDTO> getPublishings() {
        return publishings;
    }

    /**
     * Устанавливает список журналов/газет.
     *
     * @param newPublishings List<PublishingDTO>
     */
    public void setPublishing(final List<PublishingDTO> newPublishings) {
        this.publishings = newPublishings;
    }

    /**
     * Возвращает список рубрик.
     *
     * @return List<Topic>
     */
    public List<Topic> getTopics() {
        return topics;
    }

    /**
     * Устанавливает список рубрик.
     *
     * @param topics List<Topic>
     */
    public void setTopics(final List<Topic> topics) {
        this.topics = topics;
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
     * Возвращает текст статьи.
     *
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает текст статьи.
     *
     * @param content String
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Возвращает список утверждённых соавторов.
     *
     * @return List<Employee>
     */
    public List<Employee> getCurrentCoauthors() {
        return currentCoauthors;
    }

    /**
     * Устанавливает список утверждённых соавторов.
     *
     * @param currentCoauthors List<Employee>
     */
    public void setCurrentCoauthors(final List<Employee> currentCoauthors) {
        this.currentCoauthors = currentCoauthors;
    }

    /**
     * Возвращает список возможных соавторов.
     *
     * @return List<Employee>
     */
    public List<Employee> getAvailableCoauthors() {
        return availableCoauthors;
    }

    /**
     * Устанавливает список возможных соавторов.
     *
     * @param availableCoauthors List<Employee>
     */
    public void setAvailableCoauthors(final List<Employee> availableCoauthors) {
        this.availableCoauthors = availableCoauthors;
    }

    /**
     * Возвращает список рецензий.
     *
     * @return List<String>
     */
    public Map<Employee, Review> getReviews() {
        return reviews;
    }

    /**
     * Устанавливает список рецензий.
     *
     * @param reviews List<Review>
     */
    public void setReviews(final Map<Employee, Review> reviews) {
        this.reviews = reviews;
    }
}
