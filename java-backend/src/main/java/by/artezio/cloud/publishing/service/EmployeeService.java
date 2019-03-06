package by.artezio.cloud.publishing.service;

import by.artezio.cloud.publishing.domain.Employee;

import java.util.Set;

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
}
