package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.service.SessionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер, возвращающий страницу для ввода логин/пароля.
 */
@Controller
public class LoginController {

    @Autowired
    private SessionController sessionController;

    @Autowired
    private EmployeeDao ud;

    /**
     *
     * @return название Jsp страницы
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        System.out.println(ud.getEmployeeByLoginPass("sara@mail.com", "admin"));
        return "login";
    }

    /**
     *
     * @param loginForm форма логина
     * @param result результат парсинга данных с формы
     * @param request HttpRequest
     * @param response HttpResponse
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@Valid @ModelAttribute final LoginForm loginForm, final BindingResult result,
            final HttpServletRequest request,
            final HttpServletResponse response) {

        //boolean hasError = sessionController.tryCreateSession(request, response);
        //show errors!!!
    }

    /**
     *
     * @return название jsp страницы
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "loginSuccess";
    }
}
