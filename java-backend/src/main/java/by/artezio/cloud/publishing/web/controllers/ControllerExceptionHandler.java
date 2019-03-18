package by.artezio.cloud.publishing.web.controllers;

import java.rmi.AccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Sergeev Evgeniy
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AccessException.class)
    public void handleForbiddenAction() {
    }
}
