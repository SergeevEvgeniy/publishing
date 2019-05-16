package by.artezio.cloud.publishing.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер страницы индивидуальных настроек пользователя.
 *
 * @author Sergeev Evgeniy
 */
@Controller
@RequestMapping("/settings")
public class SettingController {

    /**
     * Метод возвращает пользователю страничку с настройками.
     *
     * @return имя страницы настроек
     */
    @GetMapping
    public final String initSettings() {
        return "settings";
    }

    /**
     *
     * @return возвращает пользователя на домашнюю страницу
     */
    @PostMapping
    public String updateSettings() {
        return "redirect:/home";
    }
}
