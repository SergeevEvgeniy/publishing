package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность для Рассылки.
 * @author vgamezo
 */
public class Mailing {

    private int id;
    private int publishingId;

    /**
     * Возвращает id рассылки.
     * @return id рассылки
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает id рассылки.
     * @param id id рассылки
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Возвращает id публикации {@link Publishing}, на которую была сделана данная рассылка.
     * @return id публикации {@link Publishing}
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id публикации {@link Publishing}, на которую была сделана данная рассылка.
     * @param publishingId id публикации {@link Publishing}
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }
}
