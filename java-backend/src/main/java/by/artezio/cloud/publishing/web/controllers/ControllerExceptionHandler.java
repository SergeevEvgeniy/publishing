package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.web.security.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Служит для обработки выбрасываемых исключений.
 *
 * @author Sergeev Evgeniy
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Логика обработки отсутствия необходимой роли у пользователя для доступа к
     * ресурсу.
     *
     * @return название страницы с сообщением
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String handleForbiddenAction() {
        return "403";
    }
}
