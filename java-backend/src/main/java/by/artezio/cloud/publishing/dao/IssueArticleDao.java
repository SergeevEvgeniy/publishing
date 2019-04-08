package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.IssueArticle;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;


/**
 * Класс для обращения к таблице issue_article.
 * @author Igor Kuzmin
 */
@Repository
public class IssueArticleDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Конструктор с параметром.
     * @param jdbcTemplate jdbcTemplate, дает доступ к БД.
     */
    public IssueArticleDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<IssueArticle> issueArticleRowMapper = (rs, i) -> {
        IssueArticle issueArticle = new IssueArticle();
        issueArticle.setIssueId(rs.getInt("issue_id"));
        issueArticle.setArticleId(rs.getInt("article_id"));
        return issueArticle;
    };

    /**
     * Получение списка {@link IssueArticle} по id номера.
     * @param issueId - id номера {@link by.artezio.cloud.publishing.domain.Issue}
     * @return список {@link IssueArticle}
     * */
    public List<IssueArticle> getIssueArticleListByIssueId(final int issueId) {
        return jdbcTemplate.query("select * from issue_article where issue_id = :issueId",
            Collections.singletonMap("issueId", issueId), issueArticleRowMapper);
    }

}
