package by.artezio.cloud.publishing.web.security.impl;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.web.security.AccessDeniedException;
import by.artezio.cloud.publishing.web.security.EncoderService;
import by.artezio.cloud.publishing.web.security.SecurityService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Имплементация SecurityService.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class SessionSecurityService implements SecurityService {

    private static final String CURRENT_USER = "currentUser";
    private static final String EDITOR = "E";
    private static final String JOURNALIST = "J";
    private static final int UNAUTHORIZED = 401;
    private static final int OK = 200;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EncoderService encoderService;

    /**
     *
     * @return HttpSesion текущая сессия
     */
    private HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest().getSession();
    }

    @Override
    public User getCurrentUser() {
        User user = (User) getSession().getAttribute(CURRENT_USER);
        return user;
    }

    @Override
    public AuthenticationResult loginUser(final LoginForm loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        String encodePass = encoderService.encode(password);

        Employee employee = employeeService.getEmployeeByLoginPass(email, encodePass);

        if (employee == null) {
            return new AuthenticationResult("Incorrect login/password", UNAUTHORIZED);
        }

        User user = new User(employee);

        getSession().setAttribute(CURRENT_USER, user);
        return new AuthenticationResult("success", OK);
    }

    @Override
    public void checkIsEditor() {
        final User currentUser = getCurrentUser();
        if (currentUser == null || !currentUser.getType().equals(EDITOR)) {
            throw new AccessDeniedException("Пользователь не обладает ролью редактор");
        }
    }

    @Override
    public void checkIsJournalist() {
        final User currentUser = getCurrentUser();
        if (currentUser == null || !currentUser.getType().equals(JOURNALIST)) {
            throw new AccessDeniedException("Пользователь не обладает ролью журналист");
        }
    }

    @Override
    public void checkIsChiefEditor() {
        final User currentUser = getCurrentUser();
        if (currentUser == null || !currentUser.isChiefEditor()) {
            throw new AccessDeniedException("Пользователь не обладает ролью Главный редактор");
        }
    }
}
