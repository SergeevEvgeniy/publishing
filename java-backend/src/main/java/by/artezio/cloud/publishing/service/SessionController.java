package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.service.employee.FakeEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergeev Evgeniy.
 */
@Component
public class SessionController {

    @Autowired
    private FakeEmployeeService userService;

    /**
     *
     *
     * @param request HttpReq
     * @param response HttpResp
     * @return true
     */
    public boolean tryCreateSession(final HttpServletRequest request,
            final HttpServletResponse response) {
//
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        Employee user = userService.getEmployee(email, password);
//
//        if (user == null) {
//            return false;
//        }
//
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(30 * 60);
//        Cookie userName = new Cookie("user", user.getEmail());
//        userName.setMaxAge(30 * 60);
//
//        String sessionID = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("JSESSIONID")) {
//                    sessionID = cookie.getValue();
//                }
//            }
//        }
//        session.setAttribute("user", user.getFirstName());
//        session.setAttribute("sessionID", sessionID);
//        response.addCookie(userName);
//
        return true;
    }
}
