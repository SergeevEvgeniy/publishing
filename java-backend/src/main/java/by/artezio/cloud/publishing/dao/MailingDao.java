package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.dto.MailingInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    private RowMapper<MailingInfo> mailingInfoRowMapper = (rs, i) -> {
        System.out.println(rs.getDate("date"));
        return new MailingInfo(
            rs.getInt("mailing_id"),
            rs.getInt("publishing_id"),
            rs.getInt("issue_id"),
            Timestamp.valueOf(rs.getTimestamp("date").toLocalDateTime()),
            rs.getString("result")
        );
    };

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
     * Возвращает список email адресов, которые подписаны на издание с id == publishingId.
     *
     * @param publishingId id публикации
     * @return Список email адресов, которые были в последней рассылке публикации с id == publishingId
     */
    public List<String> getEmailList(final int publishingId) {
        return jdbcTemplate.query(
            "select ms.email\n"
                + "from mailing m join mailing_subscriber ms on m.id = ms.mailing_id\n"
                + "where publishing_id = :publishing_id;",
            new MapSqlParameterSource(Collections.singletonMap("publishing_id", publishingId)),
            (rs, rowNum) -> rs.getString(1)
        );
    }

    /**
     * Возвращает информацию о всех произошедших рассылках.
     *
     * @return список объектов {@link MailingInfo}.
     */
    public List<MailingInfo> getAllMailingInfo() {
        return jdbcTemplate.query(
            "select mailing_id, publishing_id, issue_id, `date`, result\n"
                + "from mailing\n"
                + "       join mailing_result on mailing.id = mailing_result.mailing_id",
            mailingInfoRowMapper
        );
    }

    /**
     * Возвращает id рассылки, привязанной к изданию с id == publishingId.
     *
     * @param publishingId id издания, по которому проходила рассылка.
     * @return id последней рассылки рассылки. Целое число, если рассылка существует, иначе null.
     */
    public Integer getMailingIdByPublishingId(final int publishingId) {
        try {
            return this.jdbcTemplate.queryForObject(
                "select id from mailing where publishing_id = :publishingId",
                Collections.singletonMap("publishingId", publishingId),
                (rs, index) -> rs.getInt("id"));
        } catch (DataAccessException ex) {
            return null;
        }
    }

    /**
     * Добавляет email-адрес подписчика к уже добавленным на рассылку с id == mailingId.
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
                "insert into mailing_subscriber (mailing_id, email) values (:mailingId, :email)",
                map
            );
        } catch (DataAccessException ex) {
            System.err.println(ex.getMessage());
            countAffectedRows = 0;
        }
        return countAffectedRows == 1;
    }

    /**
     * Создается новая запись в таблице mailing, которая привязывается к изданию с id == publishingId
     *      и возвращается id этой рассылки.
     *
     * @param publishingId id издания, на которую создается рассылка.
     * @return id рассылки.
     */
    public Integer createMailingRecord(final int publishingId) {
        this.jdbcTemplate.update(
            "insert into mailing (publishing_id) values (:publishingId)",
            Collections.singletonMap("publishingId", publishingId)
        );
        return this.getMailingIdByPublishingId(publishingId);
    }

    /**
     * Удаляет всех подписчиков, которые подписаны на рассылку с id == mailingId.
     * @param mailingId id рассылки.
     */
    public void clearMailingSubscribersByMailingId(final Integer mailingId) {
        this.jdbcTemplate.update(
            "delete from mailing_subscriber where mailing_id = :mailing_id",
            Collections.singletonMap("mailing_id", mailingId)
        );
    }
}
