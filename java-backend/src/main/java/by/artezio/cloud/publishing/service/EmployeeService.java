package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий логику получения данных Employee.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao userDao;

    /**
     *
     * @param email значение почты
     * @param password значение пароля
     * @return Employee
     */
    public Employee getEmployee(final String email, final String password) {
        //захэшировать пароль
        return userDao.getEmployeeByLoginPass(email, password);
    }
}
