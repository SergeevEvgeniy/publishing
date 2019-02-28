package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность для подписчиков рассылки.
 * @author vgamezo
 */
public class MailingSubscriber {

    private int mailingId;
    private String email;

    /**
     * Возвращает id рассылки {@link Mailing}.
     * @return id рассылки
     */
    public int getMailingId() {
        return mailingId;
    }

    /**
     * Устанавливает id рассылки {@link Mailing}.
     * @param mailingId id рассылки
     */
    public void setMailingId(final int mailingId) {
        this.mailingId = mailingId;
    }

    /**
     * Возвращает email-адрес подписчика.
     * @return email-адрес подписчика
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает email-адрес подписчика.
     * @param email email-адрес подписчика
     */
    public void setEmail(final String email) {
        this.email = email;
    }
}
