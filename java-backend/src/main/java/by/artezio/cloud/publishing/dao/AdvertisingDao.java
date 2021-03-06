package by.artezio.cloud.publishing.dao;


import by.artezio.cloud.publishing.domain.Advertising;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

/**
 * Класс для обращения к таблице advertising.
 * @author Igor Kuzmin
 * */
@Repository
public class AdvertisingDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Advertising> advertisingRowMapper = (rs, i) -> {
        Advertising advertising = new Advertising();
        advertising.setId(rs.getInt("id"));
        advertising.setIssueId(rs.getInt("issue_id"));
        advertising.setFilePath(rs.getString("file_path"));
        return advertising;
    };

    /**
     * Конструктор с параметрами.
     * @param jdbcTemplate - дает доступ к БД.
     * */
    public AdvertisingDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Метод получения списк {@link Advertising} по id номера.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * @return список {@link Advertising}
     * */
    public List<Advertising> getAdvertisingListByIssueId(final int issueId) {
        return jdbcTemplate.query("select * from advertising where issue_id = :issueId",
            Collections.singletonMap("issueId", issueId), advertisingRowMapper);
    }

    /**
     * Метод удаления {@link Advertising} по id номера.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    public void deleteAdvertisingByIssueId(final int issueId) {
        jdbcTemplate.update("delete from advertising where issue_id = :id",
            Collections.singletonMap("id", issueId));
    }

    /**
     * Метод для вставки рекламы в бд.
     * @param advertising - {@link Advertising}.
     * */
    public void insertAdvertising(final Advertising advertising) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(advertising);
        jdbcTemplate.update("insert into advertising(issue_id, file_path) "
            + "values(:issueId, :filePath)", params);
    }

}
