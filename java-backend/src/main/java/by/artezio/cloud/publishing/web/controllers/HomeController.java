package by.artezio.cloud.publishing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер, возвращающий jsp страницы.
 */
@Controller
public class HomeController {

    /**
     * Метод возвращает пользователю домашнюю страницу.
     *
     * @return имя домашней jsp страницы
     */
    @GetMapping(path = "/home")
    public final String home() {
        return "home";
    }
}
