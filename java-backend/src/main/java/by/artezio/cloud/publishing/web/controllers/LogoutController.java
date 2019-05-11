package by.artezio.cloud.publishing.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер возвращающий страницу выхода.
 *
 */
@Controller
public class LogoutController {

    /**
     * Метод завершает сессию и возвращает пользователю страницу для входа в
     * систему.
     *
     * @param request req
     * @param response res
     * @throws javax.servlet.ServletException se
     * @throws java.io.IOException io
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("login");
    }
}
