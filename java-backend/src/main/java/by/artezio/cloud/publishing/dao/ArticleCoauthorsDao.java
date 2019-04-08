package by.artezio.cloud.publishing.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Класс для обращения к таблице article_coauthors.
 *
 * @author Denis Shubin
 */
@Repository
public class ArticleCoauthorsDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     *
     * @param jdbcTemplate {@link NamedParameterJdbcTemplate}
     */
    public ArticleCoauthorsDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * @param articleId id статьи
     * @return список id соавторов статьи
     */
    public List<Integer> getCoauthorsIdByArticleId(final int articleId) {
        return jdbcTemplate.queryForList("SELECT employee_id FROM article_coauthors\n"
                + "WHERE article_id = :articleId",
            Collections.singletonMap("articleId", articleId), Integer.class);
    }
}
