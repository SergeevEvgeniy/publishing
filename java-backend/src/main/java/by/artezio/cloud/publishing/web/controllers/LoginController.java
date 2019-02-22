package by.artezio.cloud.publishing.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер, возвращающий страницу для ввода логин/пароля.
 */
@Controller
public class LoginController {

    private final String userID = "admin";
    private final String password = "admin";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException, ServletException {

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (userID.equals(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);

            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                }
            }
            session.setAttribute("user", userName);
            session.setAttribute("sessionID", sessionID);
            response.addCookie(userName);
            response.sendRedirect("success");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User name or password is wrong.</font>");
            rd.include(request, response);
        }
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "loginSuccess";
    }
}
