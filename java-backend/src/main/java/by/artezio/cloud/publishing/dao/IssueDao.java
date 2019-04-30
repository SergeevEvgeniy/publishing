package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Issue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Класс для обращения к таблице issue.
 *
 * @author Igor Kuzmin
 */
@Repository
public class IssueDao {

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
     * Конструктор с параметром.
     *
     * @param jdbcTemplate jdbcTemplate, дает доступ к БД.
     */
    public IssueDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получение списка всех номеров.
     *
     * @return {@link List} всех {@link Issue}.
     */
    public List<Issue> getListOfAllIssues() {
        return jdbcTemplate.query("select * from issue", issueRowMapper);
    }

    /**
     * Удаление {@link Issue} из базы данных.
     *
     * @param id - идентификатор номера.
     */
    public void deleteIssueById(final int id) {
        jdbcTemplate.update("delete from issue where id = :id",
            Collections.singletonMap("id", id));
    }

    /**
     * Получение номера по id.
     *
     * @param id - идентификатор {@link Issue}.
     * @return {@link Issue}.
     */
    public Issue getIssueById(final int id) {
        return jdbcTemplate.queryForObject("select * from issue where id = :id",
            Collections.singletonMap("id", id), issueRowMapper);
    }

    /**
     * Возвращает списоок всех номеров, дата выпуска которых равна date.
     *
     * @param date Дата выпуска.
     * @return Список номеров.
     */
    public List<Issue> getIssueByDate(final LocalDate date) {
        return jdbcTemplate.query(
            "select * from issue i where i.date = :date",
            Collections.singletonMap("date",
                date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth()),
            issueRowMapper
        );
    }

    /**
     * Метод получения всех номеров определенного журнала/газеты.
     *
     * @param publishingId - идентификатор журнала/газеты.
     * @return список {@link Issue} журнала/газеты.
     */
    public List<Issue> getIssueListByPublishingId(final int publishingId) {
        return jdbcTemplate.query("select * from issue "
                + "where publishing_id = :publishingId",
            Collections.singletonMap("publishingId", publishingId), issueRowMapper);
    }

    /**
     * Метод для вставки номера в бд.
     *
     * @param issue - {@link Issue}.
     * @return - id номера, который сгенерирован в результате вставки.
     */
    public Integer insertIssue(final Issue issue) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(issue);
        jdbcTemplate.update("insert into issue(publishing_id, number, date, published) "
            + "values(:publishingId, :number, :date, :published)", params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * Метод для обновления номера в бд.
     *
     * @param newIssue - обновленная информация по {@link Issue}.
     */
    public void updateIssue(final Issue newIssue) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(newIssue);
        jdbcTemplate.update("update issue set number=:number, date=:date, published=:published where id=:id",
            params);
    }

    /**
     * Проверка, опубликована ли статья с указанным id.
     *
     * @param articleId id статьи
     * @return {@code true}, если статья с данным id опубликована, иначе - {@code false}
     */
    public boolean isArticlePublished(final int articleId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM issue_article WHERE article_id = :articleId",
            Collections.singletonMap("articleId", articleId), Integer.class) >= 1;
    }
}
