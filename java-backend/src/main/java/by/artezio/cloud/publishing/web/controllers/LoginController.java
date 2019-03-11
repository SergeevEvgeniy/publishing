package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import static by.artezio.cloud.publishing.web.service.impl.AuthenticationInterceptor.LOCATION;
import by.artezio.cloud.publishing.web.service.SecurityService;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping(value = LOGIN_LOCATION)
    public String login(final Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    /**
     *
     * @param loginForm форма логина
     * @param result результат парсинга данных с формы
     * @param model Model
     * @param request request
     *
     * @return ModelAndView статьи, либо логин, в случае неудачи аутентификации
     */
    @PostMapping(value = LOGIN_LOCATION)
    public String login(@Valid final LoginForm loginForm,
            final BindingResult result,
            final Model model,
            final HttpServletRequest request) {

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

        final Object previousLink = request.getSession().getAttribute(LOCATION);
        if (previousLink != null) {
            return "redirect:" + previousLink;
        }

        return "home";
    }
}
