package by.artezio.cloud.publishing.service.login;

import by.artezio.cloud.publishing.domain.User;
import by.artezio.cloud.publishing.service.user.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionController {

    @Autowired
    UserService userService;

    public boolean tryCreateSession(HttpServletRequest request,
            HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUser(email, password);

        if (user == null) {
            return false;
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60);
        Cookie userName = new Cookie("user", user.getEmail());
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
        session.setAttribute("user", user.getName());
        session.setAttribute("sessionID", sessionID);
        response.addCookie(userName);

        return true;
    }
}
