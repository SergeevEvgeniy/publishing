package by.artezio.cloud.publishing.web.service;

import by.artezio.cloud.publishing.web.controllers.LoginController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Sergeev Evgeniy
 */
@Service
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final SecurityService securityService;

    /**
     *
     * @param securityService сервис аутентификации
     */
    public AuthenticationInterceptor(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
            final Object handler) throws Exception {

        if (securityService.getCurrentUser() == null) {
            response.sendRedirect(request.getContextPath() + LoginController.LOGIN_LOCATION);
        }
        HttpSession session = request.getSession();
        session.setAttribute("location", request.getPathInfo());
        return true;
    }
}
