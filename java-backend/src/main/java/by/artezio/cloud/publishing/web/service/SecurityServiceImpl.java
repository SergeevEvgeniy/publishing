package by.artezio.cloud.publishing.web.service;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.EmployeeService;
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
public class SecurityServiceImpl implements SecurityService {

    private static final String CURRENT_USER = "currentUser";
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
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

        String encodePass = password;//encoderService.encode(password);

        Employee employee = employeeService.getEmployeeByLoginPass(email, encodePass);

        if (employee == null) {
            return new AuthenticationResult("Incorrect login/password", UNAUTHORIZED);
        }

        User user = new User(employee);

        getSession().setAttribute(CURRENT_USER, user);
        return new AuthenticationResult("success", OK);
    }
}
