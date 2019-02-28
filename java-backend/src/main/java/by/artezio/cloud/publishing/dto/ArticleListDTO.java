package by.artezio.cloud.publishing.dto;

import java.util.List;

/**
 * Класс для переноса данных из контроллера в article_list.jsp.
 *
 * @author Denis Shubin
 */
public class ArticleListDTO {

    private String title;
    private String publishing;
    private String topic;
    private String authorFullName;
    private List<String> coauthors;

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
     * @param publishing String
     */
    public void setPublishing(final String publishing) {
        this.publishing = publishing;
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
     * @return List<String>
     */
    public List<String> getCoauthors() {
        return coauthors;
    }

    /**
     * Устанавливает список соавторов.
     *
     * @param coauthors List<String>
     */
    public void setCoauthors(final List<String> coauthors) {
        this.coauthors = coauthors;
    }
}
