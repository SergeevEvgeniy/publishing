package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Publishing;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Component
public class PublishingDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Publishing> publishingRowMapper = (rs, i) -> new Publishing(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("type").charAt(0),
        rs.getString("subjects")
    );

    public PublishingDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Publishing> getPublishingList() {
        return jdbcTemplate.query(
            "select * from publishing", publishingRowMapper);
    }

    public Publishing getPublishingById(int id) {
        return jdbcTemplate.queryForObject(
            "select * from publishing where id = :id",
            Collections.singletonMap("id", id),
            publishingRowMapper
        );
    }
}
