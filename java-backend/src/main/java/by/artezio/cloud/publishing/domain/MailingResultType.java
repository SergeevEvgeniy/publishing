package by.artezio.cloud.publishing.domain;

/**
 * Типы результатов рассылок.
 *
 * @author vgamezo
 */
public enum MailingResultType {

    SUCCESS("Успешно"),
    BAD_SUBSCRIBER("Ошибка при отсылке письма по адресу"),
    UNKNOWN_ERROR("Непредвиденная ошибка.");

    private String message;

    MailingResultType(final String message) {
        this.message = message;
    }

    /**
     * Возвращает текстовое сообщение каждого отдельного сообщения.
     *      Например, "Успешно"
     * @return текстовое значение результата
     */
    public String getMessage() {
        return this.message;
    }
}
