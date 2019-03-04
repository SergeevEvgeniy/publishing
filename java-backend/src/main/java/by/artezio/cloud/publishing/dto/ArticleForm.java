package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Review;

import java.util.List;

/**
 * Класс, содержащий детальную информацию о статье.
 *
 * <p>Используется для отображения статей на странице <code>update_article.jsp</code>.
 *
 * @author Denis Shubin
 */
public class ArticleForm {

    private List<String> publishings;
    private List<String> topics;
    private String title;
    private String content;
    private List<String> currentCoauthors;
    private List<String> availableCoauthors;
    private List<Review> reviews;

    /**
     * Возвращает список журналов/газет.
     *
     * @return List<String>
     */
    public List<String> getPublishings() {
        return publishings;
    }

    /**
     * Устанавливает список журналов/газет.
     *
     * @param publishings List<String>
     */
    public void setPublishings(final List<String> publishings) {
        this.publishings = publishings;
    }

    /**
     * Возвращает список рубрик.
     *
     * @return List<String>
     */
    public List<String> getTopics() {
        return topics;
    }

    /**
     * Устанавливает список рубрик.
     *
     * @param topics List<String>
     */
    public void setTopics(final List<String> topics) {
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
     * @return List<String>
     */
    public List<String> getCurrentCoauthors() {
        return currentCoauthors;
    }

    /**
     * Устанавливает список утверждённых соавторов.
     *
     * @param currentCoauthors List<String>
     */
    public void setCurrentCoauthors(final List<String> currentCoauthors) {
        this.currentCoauthors = currentCoauthors;
    }

    /**
     * Возвращает список возможных соавторов.
     *
     * @return List<String>
     */
    public List<String> getAvailableCoauthors() {
        return availableCoauthors;
    }

    /**
     * Устанавливает список возможных соавторов.
     *
     * @param availableCoauthors List<String>
     */
    public void setAvailableCoauthors(final List<String> availableCoauthors) {
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
