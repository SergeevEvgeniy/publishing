package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность для Рассылки.
 * @author vgamezo
 */
public class Mailing {

    private Integer id;
    private Integer publishingId;

    /**
     * Конструктор по умолчанию.
     */
    public Mailing() {
    }

    /**
     * Конструктор с параметрами.
     * @param id id рассылки.
     * @param publishingId id издания, на которую эта рассылка привязана.
     */
    public Mailing(final Integer id, final Integer publishingId) {
        this.id = id;
        this.publishingId = publishingId;
    }

    /**
     * Возвращает id рассылки.
     * @return id рассылки
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id рассылки.
     * @param id id рассылки
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Возвращает id публикации, на которую была сделана данная рассылка.
     * @return id публикации
     */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id публикации, на которую была сделана данная рассылка.
     * @param publishingId id публикации
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }
}
