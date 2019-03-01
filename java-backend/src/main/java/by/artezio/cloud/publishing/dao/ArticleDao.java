package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Article;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ArticleDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Article> rowMapper = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setPublishingId(rs.getInt("publishing_id"));
        article.setTopicId(rs.getInt("topic_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setAuthorId(rs.getInt("author_id"));
        return article;
    };

    /**
     * Конструктор с параметром {@param jdbcTemplate}
     * @param jdbcTemplate
     */
    public ArticleDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getArticleListByJournalistId(final int authorId) {
        return jdbcTemplate.query("SELECT * FROM article\n"
                + "WHERE author_id = :authorId",
            Collections.singletonMap("authorId", authorId), rowMapper);
    }

    public List<Article> getAllArticles() {
        return jdbcTemplate.query("SELECT * FROM article", rowMapper);
    }
}
