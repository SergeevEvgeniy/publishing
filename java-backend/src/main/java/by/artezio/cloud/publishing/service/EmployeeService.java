package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;

import java.util.List;

/**
 * Сервис, реализующий бизнес-логику по обработке сущности {@link Employee}.
 *
 * @author Sergeev Evgeniy
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
     * Получение списка сотрудников по идентификатору журнала/газеты, в которой
     * они работают.
     *
     * @param id идентификатор журнала/газеты
     * @return {@link List}&lt;{@link Employee}&gt;
     */
    List<Employee> getEmployeesByPublishingId(final Integer id);

    /**
     * Получение списка сотрудников по идентификатору журнала/газеты, в которой
     * они работают.
     *
     * @param email    значение почты
     * @param password значение пароля
     * @return Employee
     */
    Employee getEmployee(final String email, final String password);

    /**
     * @param email          значение почты
     * @param encodePassword закэшированное значение пароля
     * @return Employee
     */
    Employee getEmployeeByLoginPass(String email, String encodePassword);

    /**
     * @param publishingId id журнала
     * @return {@link List} of {@link EmployeeShortInfo}
     */
    List<EmployeeShortInfo> getShortEmployeeList(Integer publishingId);

    /**
     * @param employeeId id сотрудника
     * @return {@link EmployeeShortInfo}
     */
    EmployeeShortInfo getShortEmployee(Integer employeeId);
}
