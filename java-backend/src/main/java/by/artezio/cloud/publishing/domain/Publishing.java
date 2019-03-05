package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность для Публикаций (газеты/журналы).
 * @author vgamezo
 */
public class Publishing {

    private Integer id;
    private String title;
    private Character type;
    private String subjects;

    /**
     * Конструктор по умолчанию.
     */
    public Publishing() {
    }

    /**
     * Конструктор с полным набором параметров для создания сущности Публикация.
     * @param id id публикации
     * @param title название
     * @param type тип публикации. Газета или Журнал.
     * @param subjects тематика публикации
     */
    public Publishing(final Integer id, final String title, final Character type, final String subjects) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.subjects = subjects;
    }

    /**
     * Возвращает id публикации.
     * @return id публикации
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id публикации.
     * @param id id публикации
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Возвращает название публикации.
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название публикации.
     * @param title название публикации
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Возвращает тип публикации.
     * @return тип публикации
     */
    public Character getType() {
        return type;
    }

    /**
     * Устанавливает тип публикации.
     * @param type тип публикации
     */
    public void setType(final Character type) {
        this.type = type;
    }

    /**
     * Возвращает тематики публикации.
     * @return тематики публикации
     */
    public String getSubjects() {
        return subjects;
    }

    /**
     * Устанавливает тематики публикации.
     * @param subjects тематики публикации
     */
    public void setSubjects(final String subjects) {
        this.subjects = subjects;
    }
}