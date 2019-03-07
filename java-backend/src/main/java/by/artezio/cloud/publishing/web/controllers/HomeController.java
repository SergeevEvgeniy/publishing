package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.web.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Контроллер главной ТЕСТОВОЙ страницы НЕ УДАЛЯТЬ!
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

    @Autowired
    private SecurityService securityService;

    /**
     * Добавляет к jsp-странице атрибут User-dto.
     *
     * @return User-dto
     */
    @ModelAttribute("user")
    public User getUser() {
        return securityService.getCurrentUser();
    }
}
