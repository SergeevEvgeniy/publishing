package by.artezio.cloud.publishing.web.security;

/**
 * Ошибка доступа для пользователя. Бросать в случае отсутствия необходимой роли
 * у пользователя, либо другого параметра, связанного с доступам к ресурсу
 *
 * @author Sergeev Evgeniy
 */
public class AccessDeniedException extends RuntimeException {

    /**
     * Конструктор по умолчанию с параметром (сообщение об ошибке).
     *
     * @param message сообщение об ошибке
     */
    public AccessDeniedException(final String message) {
        super(message);
    }
}
