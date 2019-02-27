package by.artezio.cloud.publishing.web.controllers;

import by.artezio.cloud.publishing.dao.user.UserDao;
import by.artezio.cloud.publishing.domain.LoginForm;
import by.artezio.cloud.publishing.service.login.SessionController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер, возвращающий страницу для ввода логин/пароля.
 */
@Controller
public class LoginController {

    @Autowired
    SessionController sessionController;

    @Autowired
    UserDao ud;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        System.out.println(ud.getUserByLoginPass("sara@mail.com", "admin"));
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result,
            final HttpServletRequest request,
            final HttpServletResponse response) {

        boolean hasError = sessionController.tryCreateSession(request, response);
        //show errors!!!        
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "loginSuccess";
    }
}
