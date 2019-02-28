package by.artezio.cloud.publishing.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * MailingDAO, дающий возможность получить данные о рассылках.
 *
 * @author vgamezo
 */
@Repository
public class MailingDao {

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
     * Возвращает список email адресов, которые были в последней рассылке публикации с {@param publishingId}.
     *
     * @param publishingId id публикации
     * @return Список email адресов, которые были в последней рассылке публикации с {@param publishingId}
     */
    public List<String> getEmailListByPublishingId(final int publishingId) {
        return jdbcTemplate.query(
            "select email\n"
                + "from mailing_subscriber ms\n"
                + "   join (select max(mailing.id) as id\n"
                + "        from mailing\n"
                + "        where publishing_id = :publishingId\n"
                + "        group by publishing_id) maxid on maxid.id = ms.mailing_id;",
            new MapSqlParameterSource(Collections.singletonMap("publishingId", publishingId)),
            (rs, rowNum) -> rs.getString(1)
        );
    }
}
