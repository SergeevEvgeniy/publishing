package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;
import by.artezio.cloud.publishing.service.EmployeeService;
import by.artezio.cloud.publishing.service.converter.EmployeeToEmployeeShortInfoConverter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация сервиса, реализующий бизнес-логику по обработке сущности
 * {@link Employee}.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class FakeEmployeeService implements EmployeeService {

    private EmployeeDao employeeDao;
    private EmployeeToEmployeeShortInfoConverter employeeConverter;

    /**
     * @param employeeDao       {@link EmployeeDao}
     * @param employeeConverter {@link EmployeeToEmployeeShortInfoConverter}
     */
    public FakeEmployeeService(final EmployeeDao employeeDao,
                               final EmployeeToEmployeeShortInfoConverter employeeConverter) {
        this.employeeDao = employeeDao;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public Employee getEmployeeById(final Integer employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByPublishingId(final Integer publishingId) {
        return employeeDao.getJournalistsByPublishingId(publishingId);
    }

    @Override
    public Employee getEmployee(final String email, final String password) {
        Employee employeeByLoginPass = employeeDao.getEmployeeByLoginPass(email, DigestUtils.md5Hex(password));
        return employeeByLoginPass;
    }

    @Override
    public Employee getEmployeeByLoginPass(final String email, final String encodePassword) {
        return employeeDao.getEmployeeByLoginPass(email, encodePassword);
    }

    @Override
    public List<EmployeeShortInfo> getShortEmployeeList(final Integer publishingId) {
        List<Employee> employees = employeeDao.getEmployeesByPublishingId(publishingId);
        List<EmployeeShortInfo> employeeShortInfos = new ArrayList<>(employees.size());
        for (Employee e : employees) {
            EmployeeShortInfo esi = employeeConverter.convert(e);
            employeeShortInfos.add(esi);
        }
        return employeeShortInfos;
    }

    @Override
    public EmployeeShortInfo getShortEmployee(final Integer employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        return employeeConverter.convert(employee);
    }
}
