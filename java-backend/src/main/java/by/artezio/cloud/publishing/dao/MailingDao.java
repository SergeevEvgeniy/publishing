package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Mailing;
import by.artezio.cloud.publishing.domain.MailingResult;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * MailingDAO, дающий возможность получить данные о рассылках (результаты, подписчики...).
 *
 * @author vgamezo
 */
@Repository
public class MailingDao {

    private RowMapper<MailingResult> mailingResultRowMapper = (rs, i) -> new MailingResult(
        rs.getInt("mailing_id"),
        rs.getInt("issue_id"),
        rs.getTimestamp("date").toLocalDateTime(),
        rs.getString("result")
    );

    private RowMapper<Mailing> mailingRowMapper = (rs, i) -> new Mailing(
        rs.getInt("id"),
        rs.getInt("publishing_id")
    );

    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Конструктор с параметром {@param jdbcTemplate}.
     *
     * @param jdbcTemplate jdbcTemplate, который дает доступ к БД.
     */
    public MailingDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Возвращает список email адресов, которые подписаны на издание с <code>id == publishingId</code>.
     *
     * @param publishingId id издания
     * @return Список email адресов подписчиков, которые подписаны на издание <code>id == publishingId</code>
     */
    public List<String> getEmailList(final int publishingId) {
        return jdbcTemplate.query(
            "select `ms`.`email`"
                + " from `mailing` `m` join `mailing_subscriber` `ms` on `m`.`id` = `ms`.`mailing_id`"
                + " where `publishing_id` = :publishing_id;",
            new MapSqlParameterSource(Collections.singletonMap("publishing_id", publishingId)),
            (rs, rowNum) -> rs.getString(1)
        );
    }

    /**
     * Возвращает список результатов рассылок.
     * @return список результатов рассылок
     */
    public List<MailingResult> getAllMailingResult() {
        return jdbcTemplate.query("select * from `mailing_result` `m` order by `m`.`date` desc", mailingResultRowMapper);
    }

    /**
     * Возвращает список всех рассылок, которые прикреплены к какому-либо изданию.
     * @return список объектов Mailing.
     */
    public List<Mailing> getAllMailing() {
        return jdbcTemplate.query("select * from `mailing`", mailingRowMapper);
    }

    /**
     * Возвращает id рассылки, привязанной к изданию с <code>id == publishingId</code>.
     *
     * @param publishingId id издания, по которому проходила рассылка.
     * @return id последней рассылки рассылки. Целое число, если рассылка существует, иначе <code>null</code>.
     */
    public Integer getMailingIdByPublishingId(final int publishingId) {
        try {
            return this.jdbcTemplate.queryForObject(
                "select `id` from `mailing` where `publishing_id` = :publishingId",
                Collections.singletonMap("publishingId", publishingId),
                (rs, index) -> rs.getInt("id"));
        } catch (DataAccessException ex) {
            return null;
        }
    }

    /**
     * Добавляет email-адрес подписчика к уже добавленным на рассылку с <code>id == mailingId</code>.
     *
     * @param mailingId id рассылки, которая прикреплена к изданию.
     * @param email email-адрес нового подписчика.
     * @return <code>true</code>, если подписчик был успешно добавлен, иначе <code>false</code>.
     */
    public boolean addEmailByMailingId(final int mailingId, final String email) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mailingId", mailingId);
        map.put("email", email);
        int countAffectedRows;
        try {
            countAffectedRows = this.jdbcTemplate.update(
                "insert into `mailing_subscriber` (`mailing_id`, `email`) values (:mailingId, :email)",
                map
            );
        } catch (DataAccessException ex) {
            System.err.println(ex.getMessage());
            countAffectedRows = 0;
        }
        return countAffectedRows == 1;
    }


    /**
     * Создается новая запись в таблице mailing, которая привязывается к изданию с <code>id == publishingId</code>
     *      и возвращается id этой рассылки.
     *
     * @param publishingId id издания, на которую создается рассылка.
     * @return id рассылки.
     */
    public Integer createMailingRecord(final int publishingId) {
        this.jdbcTemplate.update(
            "insert into `mailing` (`publishing_id`) values (:publishingId)",
            Collections.singletonMap("publishingId", publishingId)
        );
        return this.getMailingIdByPublishingId(publishingId);
    }

    /**
     * Удаляет всех подписчиков, которые подписаны на рассылку с <code>id == mailingId</code>.
     * @param mailingId id рассылки.
     */
    public void clearMailingSubscribersByMailingId(final Integer mailingId) {
        this.jdbcTemplate.update(
            "delete from `mailing_subscriber` where `mailing_id` = :mailing_id",
            Collections.singletonMap("mailing_id", mailingId)
        );
    }

    /**
     * Добавляет результат рассылки в базу данных. При удачном добавлении вызвращает <code>true</code>,
     * в случае возникновения какой-либо ошибки возвращает <code>false</code>.
     *
     * @param mailingId идентификатор рассылки.
     * @param issueId идентификатор номера, по которому происходила рассылка.
     * @param dateTime день, на момент которого произошла рассылка.
     * @param result результат рассылки.
     * @return <code>true</code>, если добавление результата рассылки произошла успешно, иначе <code>false</code>.
     */
    public boolean addMailingResult(final int mailingId, final int issueId, final LocalDateTime dateTime, final String result) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("mailingId", mailingId);
        map.put("issueId", issueId);
        map.put("dateTime", Timestamp.valueOf(dateTime));
        map.put("result", result);
        int affectedRows;
        try {
            affectedRows = this.jdbcTemplate.update(
                "insert into `mailing_result` values (:mailingId, :issueId, \":dateTime\", \":result\")",
                map
            );
        } catch (DataAccessException ex) {
            System.out.println(ex.getMessage());
            affectedRows = 0;
        }
        return affectedRows == 1;
    }
}
