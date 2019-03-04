package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Employee;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
     *
     * @param email значение почты
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

}
