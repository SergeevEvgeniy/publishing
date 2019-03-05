package by.artezio.cloud.publishing.dao.user;

import by.artezio.cloud.publishing.domain.User;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    RowMapper<User> mapper = (ResultSet rs, int rowNum) -> {
        return new User(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("middle_name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("sex").charAt(0),
                rs.getInt("birth_year"),
                rs.getString("address"),
                rs.getString("type").charAt(0),
                rs.getInt("education_id"),
                rs.getInt("chief_editor"));
    };

    public User getUserByLoginPass(String email, String password) {
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
