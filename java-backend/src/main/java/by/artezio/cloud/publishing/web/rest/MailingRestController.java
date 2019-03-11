package by.artezio.cloud.publishing.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер, отлавливающий запросы на совершение рассылки.
 *
 * @author vgamezo
 */
@RestController
@RequestMapping(path = "/mailing/send")
public class MailingRestController {

    /**
     * Метод, реализуюищй отлавливание действия на рассылку с начальными данными (TODO добавить начальные данные, как параметры).
     * @return true(TODO добавить норманый док)
     */
    @GetMapping(path = "")
    public boolean sendMail() {
        //TODO реализовать.
        return true;
    }
}
