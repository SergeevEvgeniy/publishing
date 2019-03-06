package by.artezio.cloud.publishing.dto;

/**
 * Класс-dto, который содержит информацио об аутентификации пользователя.
 *
 * @author Sergeev Evgeniy
 */
public class AuthenticationResult {

    private String message;

    /**
     *
     * @param message Authentication message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     *
     * @return Authentication message
     */
    public String getMessage() {
        return message;
    }
}

