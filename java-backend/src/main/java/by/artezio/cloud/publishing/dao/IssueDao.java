package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Issue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Класс для обращения к таблице issue.
 * @author Igor Kuzmin
 */
@Repository
public final class IssueDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Issue> issueRowMapper = (rs, i) -> {
        Issue issue = new Issue();
        issue.setId(rs.getInt("id"));
        issue.setPublishingId(rs.getInt("publishing_id"));
        issue.setNumber(rs.getString("number"));
        issue.setDate(rs.getDate("date").toLocalDate());
        issue.setPublished(rs.getBoolean("published"));
        return issue;
    };

    /**
     * Конструктор с параметром {@param jdbcTemplate}.
     * @param jdbcTemplate jdbcTemplate, дает доступ к БД.
     */
    private IssueDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получение списка всех номеров.
     * @return {@link List} всех {@link Issue}
     * */
    public List<Issue> getListOfAllIssues() {
        return jdbcTemplate.query("select * from issue", issueRowMapper);
    }

    /**
     * Удаление {@link Issue} из базы данных.
     * @param id - идентификатор номера.
     * */
    public void deleteIssueById(final int id) {
        jdbcTemplate.update("delete from issue where id = :id",
            Collections.singletonMap("id", id));
    }

    /**
     * Получение номера по id.
     * @param id - идентификатор {@link Issue}
     * @return {@link Issue}
     * */
    public Issue getIssueById(final int id) {
        return jdbcTemplate.queryForObject("select * from issue where id = :id",
            Collections.singletonMap("id", id), issueRowMapper);
    }

}
