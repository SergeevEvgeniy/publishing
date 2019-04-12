package by.artezio.cloud.publishing.dto;

/**
 * Класс-сущность для Публикаций (газеты/журналы).
 * @author vgamezo
 */
public class PublishingDTO {

    private Integer id;
    private String title;

    /**
     * Конструктор по умолчанию.
     */
    public PublishingDTO() {
    }

    /**
     * Конструктор с полным набором параметров для создания сущности Публикация.
     * @param id id публикации
     * @param title название
     */
    public PublishingDTO(final Integer id, final String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return "PublishingDTO{"
                    + "id=" + id
                    + ", title='" + title + '\''
            + '}';
    }
}
