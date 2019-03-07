package by.artezio.cloud.publishing.dto;

import java.util.List;

/**
 * Класс-сущность для передачи данных о подписчиках от формы к контроллеру.
 * @author vgamezo
 */
public class Subscribers {
    private int publishingId;
    private List<String> emails;

    /**
     * Конструктор по умолчанию.
     */
    public Subscribers() {
    }

    /**
     * Конструктор с параметрами.
     * @param publishingId id издательства
     * @param emails список email-адресов
     */
    public Subscribers(final int publishingId, final List<String> emails) {
        this.publishingId = publishingId;
        this.emails = emails;
    }

    /**
     * Возвращает id издательства.
     * @return id издательства.
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Устанавливает id издательства.
     * @param publishingId id издательства.
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Возвращает список email-адресов.
     * @return список email-адресов.
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Устанавливает список email-адресов.
     * @param emails список email-адресов.
     */
    public void setEmails(final List<String> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Subscribers{"
            + "publishingId=" + publishingId
            + ", emails=" + emails
            + '}';
    }
}
