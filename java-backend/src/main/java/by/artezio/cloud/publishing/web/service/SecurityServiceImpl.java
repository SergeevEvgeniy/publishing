package by.artezio.cloud.publishing.web.service;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.dto.User;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sergeev Evgeniy
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AuthenticationResult loginUser(final LoginForm loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        Employee employee = employeeService.getEmployee(email, password);
        AuthenticationResult ar = new AuthenticationResult();

        if (employee == null) {
            ar.setMessage("Incorrect login/password");
        }

        return ar;
    }

}

