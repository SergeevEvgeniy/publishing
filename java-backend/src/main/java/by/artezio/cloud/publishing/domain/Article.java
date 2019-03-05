package by.artezio.cloud.publishing.domain;

import java.util.Set;

/**
 * Класс-сущность, представляет строку из таблицы "Articles".
 *
 * @author Denis Shubin.
 */
public class Article {

    private Integer id;
    private Publishing publishing;
    private Topic topic;
    private String title;
    private String content;
    private Employee author;
    private Set<Employee> coauthors;

    /**
     * Конструктор, создаёт класс со стандартными значениями полей.
     */
    public Article() {
    }

    /**
     * Конструктор для создания объекта с указанными значениями полей.
     *
     * @param id         идентификатор статьи
     * @param publishing {@link Publishing} журнал/газета, в котором печатается эта статья
     * @param topic      {@link Topic} рубрика статьи
     * @param title      название статьи
     * @param content    текст статьи
     * @param author     {@link Employee} автор статьи
     * @param coauthors  множество соавторов {@link Employee} статьи
     */
    public Article(final Integer id, final Publishing publishing, final Topic topic,
                   final String title, final String content, final Employee author,
                   final Set<Employee> coauthors) {
        this.id = id;
        this.publishing = publishing;
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.author = author;
        this.coauthors = coauthors;
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
     * @return объект класса {@link Publishing}
     */
    public Publishing getPublishing() {
        return publishing;
    }

    /**
     * Устанавливает журнал/газету статьи.
     *
     * @param publishing объект класса {@link Publishing}
     */
    public void setPublishing(final Publishing publishing) {
        this.publishing = publishing;
    }

    /**
     * Возвращает рубрику статьи.
     *
     * @return объект класса {@link Topic}
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Устанавливает рубрику статьи.
     *
     * @param topic объект класса {@link Topic}
     */
    public void setTopic(final Topic topic) {
        this.topic = topic;
    }

    /**
     * Возвращает автора статьи.
     *
     * @return объект класса {@link Employee}
     */
    public Employee getAuthor() {
        return author;
    }

    /**
     * Устанавливает автора статьи.
     *
     * @param author объект класса {@link Employee}
     */
    public void setAuthor(final Employee author) {
        this.author = author;
    }

    /**
     * Возвращает множество соавторов статьи.
     *
     * @return {@link Set} объектов {@link Employee}
     */
    public Set<Employee> getCoauthors() {
        return coauthors;
    }

    /**
     * Устанавливает множество соавторов статьи.
     *
     * @param coauthors {@link Set} объектов {@link Employee}
     */
    public void setCoauthors(final Set<Employee> coauthors) {
        this.coauthors = coauthors;
    }
}
