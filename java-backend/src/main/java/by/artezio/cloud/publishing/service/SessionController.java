package by.artezio.cloud.publishing.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author Sergeev Evgeniy.
 */
@Component
public class SessionController {
//
//    @Autowired
//    EmployeeService userService;
//
//    /**
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public boolean tryCreateSession(final HttpServletRequest request,
//            final HttpServletResponse response) {
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
//        return true;
//    }
}
