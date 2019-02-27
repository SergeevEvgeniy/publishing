package by.artezio.cloud.publishing.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author gamezovladislav
 * @date 22.02.19
 */
@Repository
public class MailingDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MailingDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getEmailListByPublishingId(int publishingId) {
        return jdbcTemplate.query(
            "select email\n" +
                "from mailing_subscriber ms\n" +
                "   join (select max(mailing.id) as id\n" +
                "        from mailing\n" +
                "        where publishing_id = :publishingId\n" +
                "        group by publishing_id) maxid on maxid.id = ms.mailing_id;",
            new MapSqlParameterSource(Collections.singletonMap("publishingId", publishingId)),
            (rs, rowNum) -> rs.getString(1)
        );
    }
}
