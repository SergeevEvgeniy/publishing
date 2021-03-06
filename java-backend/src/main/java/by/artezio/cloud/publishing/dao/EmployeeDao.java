package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * @param email      значение почты
     * @param encodePass хэш пароля
     * @return Null если нет такого пользователя, либо Employee полученного по
     * совпадению пары логин/пароль
     */
    public Employee getEmployeeByLoginPass(final String email, final String encodePass) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("email", email);
        map.put("password", encodePass);
        try {
            return this.jdbcTemplate.queryForObject(
                "SELECT * FROM employee where email = :email and password = :password",
                map,
                mapper
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
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
     * @return {@link List}&lt;{@link Employee}&gt; множество сотрудников,
     * которые работают в указанном журнале/газете
     */
    public List<Employee> getJournalistsByPublishingId(final Integer id) {
        String sql = "SELECT id,"
            + " first_name, last_name, middle_name,"
            + " email, password, sex, birth_year,"
            + " address, type, education_id, chief_editor"
            + " FROM employee e"
            + " INNER JOIN publishing_employee pe ON pe.employee_id = e.id"
            + " WHERE pe.publishing_id = :publishingId"
            + " AND e.type = 'J'";
        return jdbcTemplate.query(sql, Collections.singletonMap("publishingId", id),
            mapper);
    }

    /**
     * @param publishingId id журнала\газеты
     * @return список сотрудников, работающих в этом журнале
     */
    public List<Employee> getEmployeesByPublishingId(final int publishingId) {
        return jdbcTemplate.query("SELECT e.* "
                + "FROM employee e "
                + "INNER JOIN publishing_employee pe "
                + "ON pe.employee_id = e.id "
                + "WHERE pe.publishing_id = :publishingId",
            Collections.singletonMap("publishingId", publishingId),
            mapper);
    }
}
