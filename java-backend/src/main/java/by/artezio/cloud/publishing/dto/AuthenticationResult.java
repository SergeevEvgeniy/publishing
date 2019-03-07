package by.artezio.cloud.publishing.dto;

/**
 * Класс-dto, который содержит информацио об аутентификации пользователя.
 *
 * @author Sergeev Evgeniy
 */
public class AuthenticationResult {

    private final String message;
    private final int status;

    /**
     *
     * @param message сообщение аутентификации
     * @param status статус аутентификации
     */
    public AuthenticationResult(final String message, final int status) {
        this.message = message;
        this.status = status;
    }

    /**
     *
     * @return Authentication message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return int значение статуса аутентификации
     */
    public int getStatus() {
        return status;
    }
}
