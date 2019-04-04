package by.artezio.cloud.publishing.dto;

import java.util.List;

/**
 * Класс, содержащий краткую информацию о статье.
 * Используется для отображения списка статей на странице articleList.jsp.
 *
 * @author Denis Shubin
 */
public class ArticleInfo {

    private int articleId;
    private String title;
    private String publishing;
    private String topic;
    private String authorFullName;
    private List<String> coauthors;

    /**
     * Возвращает идентификатор статьи.
     *
     * @return int
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     * Устанавливает идентификатор статьи.
     *
     * @param articleId идентификатор статьи
     */
    public void setArticleId(final int articleId) {
        this.articleId = articleId;
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
     * Возвращает название журнала/газеты.
     *
     * @return String
     */
    public String getPublishing() {
        return publishing;
    }

    /**
     * Устанавливает название журнала/газеты.
     *
     * @param newPublishing String
     */
    public void setPublishingName(final String newPublishing) {
        this.publishing = newPublishing;
    }

    /**
     * Возвращает рубрику статьи.
     *
     * @return String
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Устанавливает рубрику статьи.
     *
     * @param topic String
     */
    public void setTopic(final String topic) {
        this.topic = topic;
    }

    /**
     * Возвращает полное имя автора статьи.
     *
     * @return String
     */
    public String getAuthorFullName() {
        return authorFullName;
    }

    /**
     * Устанавливает полное имя автора статьи.
     *
     * @param authorFullName String
     */
    public void setAuthorFullName(final String authorFullName) {
        this.authorFullName = authorFullName;
    }

    /**
     * Возвращает список полных имён соавторов.
     *
     * @return Set<String>
     */
    public List<String> getCoauthors() {
        return coauthors;
    }

    /**
     * Устанавливает список соавторов.
     *
     * @param coauthors Set<String>
     */
    public void setCoauthors(final List<String> coauthors) {
        this.coauthors = coauthors;
    }

    /**
     *
     * @param newPublishing {@link String} название журнала\газеты
     */
    public void setPublishing(String newPublishing) {
        this.publishing = newPublishing;
    }
}
