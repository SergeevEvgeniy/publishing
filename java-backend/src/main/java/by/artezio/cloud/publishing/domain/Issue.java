package by.artezio.cloud.publishing.domain;

import java.time.LocalDate;

/**
 * Класс-сущность Номера.
 */
public class Issue {

    private Integer id;
    private Integer publishingId;
    private String number;
    private LocalDate date;
    private boolean published;

    /**
     * Возвращает id номера.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id номера.
     * @param id id Номера
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Возвращает id публикации, связанной с номером.
     * @return id публикации, связанной с номером
     */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id публикации, связанной с номером {@link Issue}.
     * @param publishingId id Публикации
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает внутренний номер выпуска для Публикации {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @return внутренний номер выпуска для Публикации {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     */
    public String getNumber() {
        return number;
    }

    /**
     * Устанавливает внутренний номер выпуска для Публикации {@link by.artezio.cloud.publishing.dto.PublishingDTO}.
     * @param number внутренний номер выпуска для Публикации {@link by.artezio.cloud.publishing.dto.PublishingDTO}
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Возвращает дату публикации.
     * @return дата публикации
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Устанавливает дату публикации номера.
     * @param date новая дата
     */
    public void setDate(final LocalDate date) {
        this.date = date;
    }

    /**
     * Возвращает метку о готовности публикации номера.
     *  <code>true</code> если номер готов в публикацию,
     *  <code>false</code> в противном случае.
     * @return метка о готовности.
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Устанавливает готовность номера в публикацию.
     *  <code>true</code> если номер готов в публикацию,
     *  <code>false</code> в противном случае.
     * @param published метка готовности
     */
    public void setPublished(final boolean published) {
        this.published = published;
    }
}
