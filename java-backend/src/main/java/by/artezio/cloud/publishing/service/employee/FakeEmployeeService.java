package by.artezio.cloud.publishing.service.employee;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Сервис, реализующий логику получения данных Employee.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class FakeEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeDao userDao;

    /**
     * Получение сторудника по его идентификатору.
     *
     * @param employeeId идентификатор сотрудника
     * @return {@link Employee}
     */
    @Override
    public Employee getEmployeeById(final Integer employeeId) {
        return userDao.getEmployeeById(employeeId);
    }

    /**
     * Получение множества журналистов по идентификатору журнала/газеты, в котором они работают.
     *
     * @param publishingId идентификатор журнала/газеты
     * @return {@link Set}&lt;{@link Employee}&gt;
     */
    @Override
    public Set<Employee> getEmployeesByPublishingId(final Integer publishingId) {
        return userDao.getJournalistsByPublishingId(publishingId);
    }

    /**
     * Получение списка
     * сотрудников по
     * идентификатору журнала/газеты,
     * <p>
     * в которой
     * они работают.
     * <p>
     * <p>
     *
     * @param email    значение почты
     * @param password значение пароля
     * @return Employee
     */
    @Override
    public Employee getEmployee(final String email, final String password) {
        Employee employeeByLoginPass = userDao.getEmployeeByLoginPass(email, DigestUtils.md5Hex(password));
        return employeeByLoginPass;
    }
}
