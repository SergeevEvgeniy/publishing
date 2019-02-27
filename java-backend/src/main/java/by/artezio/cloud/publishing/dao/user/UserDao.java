package by.artezio.cloud.publishing.dao.user;

import by.artezio.cloud.publishing.domain.User;
import java.sql.ResultSet;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

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
        try {
            return this.jdbcTemplate
                    .queryForObject("SELECT * FROM employee where email = ? and password = ?",
                            new Object[]{email, password}, mapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

}
