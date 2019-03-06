package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс для обращения к таблице Employee.
 *
 * @author Sergeev Evgeniy
 */
@Repository
public class EmployeeDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> mapper = (ResultSet rs, int rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setMiddleName(rs.getString("middle_name"));
        employee.setEmail(rs.getString("email"));
        employee.setPassword(rs.getString("password"));
        employee.setSex(rs.getString("sex").charAt(0));
        employee.setBirthYear(rs.getInt("birth_year"));
        employee.setAddress(rs.getString("address"));
        employee.setType(rs.getString("type").charAt(0));
        employee.setEducationId(rs.getInt("education_id"));
        employee.setChiefEditor(rs.getBoolean("chief_editor"));
        return employee;
    };

    /**
     * @param email    значение почты
     * @param password значение пароля
     * @return Employee полученного по совпадению пары логин/пароль
     */
    public Employee getEmployeeByLoginPass(final String email, final String password) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("email", email);
        map.put("password", password);
        return this.jdbcTemplate.queryForObject(
            "SELECT * FROM employee where email = :email and password = :password",
            map,
            mapper
        );
    }

    /**
     * Получение сотрудника по его идентификатору.
     *
     * @param id идентификатор сотрудника
     * @return {@link Employee}
     */
    public Employee getEmployeeById(final int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = :id",
            Collections.singletonMap("id", id), mapper);
    }

    /**
     * Получение множества сотрудников по идентификатору журнала/газеты.
     *
     * @param id идентификатор журнала/газеты
     * @return {@link Set}&lt;{@link Employee}&gt; множество сотрудников, которые работают в указанном журнале/газете
     */
    public Set<Employee> getJournalistsByPublishingId(final Integer id) {
        String sql = "SELECT id,"
            + " firstName, last_name, middle_name,"
            + " email, password, sex, birth_year,"
            + " address, type, education_id, chief_editor"
            + " FROM employee e"
            + " INNER JOIN publishing_employee pe ON pe.employee_id = e.id"
            + " WHERE pe.publishing_id = :publishingId"
            + " AND e.type = 'J'";
        return new HashSet<>(jdbcTemplate.query(sql, Collections.singletonMap("publishingId", id),
            mapper));
    }
}
