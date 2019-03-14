package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Publishing;
import by.artezio.cloud.publishing.domain.Topic;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Publishing DAO, который дает возможность получать данные, связанные с
 * публикациями {@link Publishing}.
 *
 * @author vgamezo
 */
@Component
public class PublishingDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Publishing> publishingRowMapper = (rs, i) -> new Publishing(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("type").charAt(0),
            rs.getString("subjects")
    );

    private RowMapper<Topic> topicRowMapper = (rs, i) -> new Topic(
            rs.getInt("id"),
            rs.getString("name")
    );

    /**
     * Конструктор с параметром.
     *
     * @param jdbcTemplate Объект, который дает доступ к базе данных.
     */
    public PublishingDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Возвращает список Publishing {@link Publishing} объектов, которые имеются
     * в издательстве.
     *
     * @return список всех Publishing {@link Publishing} объектов
     */
    public List<Publishing> getPublishingList() {
        return jdbcTemplate.query(
                "select * from publishing", publishingRowMapper);
    }

    /**
     * Возвращает объект публикации {@link Publishing} c id == {@link id}.
     *
     * @param id - publishing id
     * @return Publishing объект, если он существует, иначе <code>null</code>.
     */
    public Publishing getPublishingById(final int id) {
        return jdbcTemplate.queryForObject(
                "select * from publishing where id = :id",
                Collections.singletonMap("id", id),
                publishingRowMapper
        );

    }

    /**
     * Возвращает список рубрик {@link Topic} входящие в состав журнала с id ==
     * {@link publishingId}.
     *
     * @param publishingId - id журнала из которого извлекаем список рубрик
     * @return List<Topic> список объектов Topic (рубрики)
     */
    public List<Topic> getTopicsByPublishingId(final int publishingId) {
        return jdbcTemplate.query("select id, name from topic "
                + "join publishing_topic pt "
                + "on topic.id = pt.topic_id "
                + "where pt.publishing_id = :publishingId",
                Collections.singletonMap("publishingId", publishingId),
                topicRowMapper
        );
    }

    /**
     * Превращение списка рубрик в строку вида "id1,id2,id3,...".
     *
     * @param topicsId список рубрик
     * @return строка вида "id1,id2,id3,..."
     */
    private String convertToString(final List<Integer> topicsId) {
        boolean needColon = false;
        StringBuilder bldr = new StringBuilder();
        for (Integer id : topicsId) {
            if (needColon) {
                bldr.append(",");
            }
            bldr.append(id);
            needColon = true;
        }
        return bldr.toString();
    }
}
