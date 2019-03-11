package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.domain.Topic;

import java.util.List;
import java.util.Set;

/**
 * Класс, содержащий детальную информацию о статье.
 *
 * <p>Используется для отображения статей на странице <code>updateArticle.jsp</code>.
 *
 * @author Denis Shubin
 */
public class ArticleForm {

    private List<Publishing> publishings;
    private List<Topic> topics;
    private String title;
    private String content;
    private Set<Employee> currentCoauthors;
    private Set<Employee> availableCoauthors;
    private List<Review> reviews;

    /**
     * Возвращает список журналов/газет.
     *
     * @return List<Publishing>
     */
    public List<Publishing> getPublishings() {
        return publishings;
    }

    /**
     * Устанавливает список журналов/газет.
     *
     * @param publishings List<Publishing>
     */
    public void setPublishings(final List<Publishing> publishings) {
        this.publishings = publishings;
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
    public Set<Employee> getCurrentCoauthors() {
        return currentCoauthors;
    }

    /**
     * Устанавливает список утверждённых соавторов.
     *
     * @param currentCoauthors List<Employee>
     */
    public void setCurrentCoauthors(final Set<Employee> currentCoauthors) {
        this.currentCoauthors = currentCoauthors;
    }

    /**
     * Возвращает список возможных соавторов.
     *
     * @return List<Employee>
     */
    public Set<Employee> getAvailableCoauthors() {
        return availableCoauthors;
    }

    /**
     * Устанавливает список возможных соавторов.
     *
     * @param availableCoauthors List<Employee>
     */
    public void setAvailableCoauthors(final Set<Employee> availableCoauthors) {
        this.availableCoauthors = availableCoauthors;
    }

    /**
     * Возвращает список рецензий.
     *
     * @return List<String>
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Устанавливает список рецензий.
     *
     * @param reviews List<Review>
     */
    public void setReviews(final List<Review> reviews) {
        this.reviews = reviews;
    }
}
