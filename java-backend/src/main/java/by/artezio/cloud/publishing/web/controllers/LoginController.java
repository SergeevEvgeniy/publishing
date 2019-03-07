package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.web.service.SecurityService;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер, возвращающий страницу для ввода логин/пароля.
 */
@Controller
public class LoginController {

    public static final String LOGIN_LOCATION = "/login";
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;

    @Autowired
    private SecurityService securityService;

    /**
     * @param model объект модели
     * @return название Jsp страницы логина
     */
    @RequestMapping(value = LOGIN_LOCATION, method = RequestMethod.GET)
    public String login(final Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    /**
     *
     * @param loginForm форма логина
     * @param result результат парсинга данных с формы
     * @param model Model
     *
     * @return ModelAndView статьи, либо логин, в случае неудачи аутентификации
     */
    @RequestMapping(value = LOGIN_LOCATION, method = RequestMethod.POST)
    public String login(@Valid final LoginForm loginForm,
            final BindingResult result,
            final Model model) {

        AuthenticationResult authResult;

        if (result.hasErrors()) {
            Logger.getLogger(LoginController.class.getName())
                    .log(Level.SEVERE, (Supplier<String>) result.getFieldErrors());
            authResult = new AuthenticationResult("Validation error", FORBIDDEN);
        } else {
            authResult = securityService.loginUser(loginForm);
        }

        if (authResult.getStatus() == UNAUTHORIZED) {
            model.addAttribute("authResult", authResult);
            return "login";
        }

        return "redirect:/home";

        // login -> home
        // some -> login -> some
        // isUser !-> login
    }
}
