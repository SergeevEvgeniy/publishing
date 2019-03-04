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
     * Возвращает id публикации {@link Publishing}, на которую была сделана данная рассылка.
     * @return id публикации {@link Publishing}
     */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id публикации {@link Publishing}, на которую была сделана данная рассылка.
     * @param publishingId id публикации {@link Publishing}
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }
}
