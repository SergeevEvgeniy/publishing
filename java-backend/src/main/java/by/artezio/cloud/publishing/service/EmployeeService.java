package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Employee}.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     *
     * @param email значение почты
     * @param encodePassword закэшированное значение пароля
     * @return Employee
     */
    public Employee getEmployeeByLoginPass(final String email, final String encodePassword) {
        return employeeDao.getEmployeeByLoginPass(email, encodePassword);
    }
}
