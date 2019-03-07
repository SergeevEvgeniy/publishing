package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Employee;

import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис, содержащий бизнес-логику по обработке сотрудников.
 */
public interface EmployeeService {

    /**
     * Получение сотрудника по его идентификатору.
     *
     * @param employeeId идентификатор сотрудника
     * @return {@link Employee}
     */
    Employee getEmployeeById(final Integer employeeId);

    /**
     * Получение списка сотрудников по идентификатору журнала/газеты,
     * в которой они работают.
     *
     * @param id идентификатор журнала/газеты
     * @return {@link Set}&lt;{@link Employee}&gt;
     */
    Set<Employee> getEmployeesByPublishingId(final Integer id);

//    /**
//     * Получение списка
//     * сотрудников по
//     * идентификатору журнала/газеты,
//     * <p>
//     * в которой
//     * они работают.
//     * <p>
//     * <p>
//     * //     * @param email значение почты
//     * //     * @param password значение пароля
//     * //     * @return Employee
//     * //
//     */
//    public Employee getEmployee(final String email, final String password) {
//        Employee employeeByLoginPass = userDao.getEmployeeByLoginPass(email, DigestUtils.md5Hex(password));
//        return employeeByLoginPass;
//    }
}
