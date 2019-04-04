package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Класс для взаимодействия с таблицей Topic в базе данных.
 */
@Repository
public class TopicDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<Topic> topicRowMapper = (rs, rowNum) -> {
        Topic t = new Topic();

        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));

        return t;
    };

    /**
     * Конструктор с параметром.
     *
     * @param jdbcTemplate объект класса {@link NamedParameterJdbcTemplate}
     */
    @Autowired
    public TopicDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получение рубрики по её идентификатору.
     *
     * @param id идентификатор рубрики
     * @return объект класса {@link Topic}
     */
    public Topic getTopicById(final int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM topic WHERE id = :topicId", Collections.singletonMap("topicId", id),
            topicRowMapper);
    }

    /**
     * @param publishingId id журнала\газеты
     * @return список рубрик для указанного журнала\газеты
     */
    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return jdbcTemplate.query("SELECT t.* "
                + "FROM topic t "
                + "INNER JOIN publishing_topic pt "
                + "ON t.id = pt.topic_id "
                + "INNER JOIN publishing p "
                + "ON p.id = pt.publishing_id "
                + "WHERE p.id = :publishingId ",
            Collections.singletonMap("publishingId", publishingId),
            topicRowMapper);
    }
}
