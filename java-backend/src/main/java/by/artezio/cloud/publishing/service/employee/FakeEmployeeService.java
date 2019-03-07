package by.artezio.cloud.publishing.service.employee;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.service.EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Имплементация сервиса, реализующий бизнес-логику по обработке сущности
 * {@link Employee}.
 *
 * @author Sergeev Evgeniy
 */
@Service
public class FakeEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(final Integer employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Set<Employee> getEmployeesByPublishingId(final Integer publishingId) {
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
}
