package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Article;
import by.artezio.cloud.publishing.domain.ArticleCoauthor;
import by.artezio.cloud.publishing.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, использующийся для взаимодействия с базой данных.
 *
 * @author Denis Shubin
 */
@Repository
public class ArticleDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Article> articleRowMapper = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setPublishingId(rs.getInt("publishing_id"));
        article.setTopicId(rs.getInt("topic_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setAuthorId(rs.getInt("author_id"));

        return article;
    };

    private RowMapper<ArticleCoauthor> articleCoauthorRowMapper = (rs, rowNum) -> {
        ArticleCoauthor ac = new ArticleCoauthor();
        ac.setEmployeeId(rs.getInt("employee_id"));
        ac.setArticleId(rs.getInt("article_id"));
        return ac;
    };

    private RowMapper<Review> reviewRowMapper = (rs, rowNum) -> {
        Review r = new Review();
        r.setReviewerId(rs.getInt("reviewer_id"));
        r.setArticleId(rs.getInt("article_id"));
        r.setContent(rs.getString("content"));
        r.setApproved(rs.getBoolean("approved"));
        return r;
    };

    /**
     * Получение списка статей по идентификатору автора.
     *
     * @param authorId int
     * @return список объектов статей {@link Article}, где указанный пользователь является автором.
     */
    public List<Article> getArticleListByJournalistId(final int authorId) {
        return jdbcTemplate.query("SELECT * FROM article\n"
                + "WHERE author_id = :authorId",
            Collections.singletonMap("authorId", authorId), articleRowMapper);
    }

    /**
     * Получение списка всех статей.
     *
     * @return список объектов статей {@link Article}.
     */
    public List<Article> getAllArticles() {
        return jdbcTemplate.query("SELECT * FROM article", articleRowMapper);
    }

    /**
     * Получение списка соавторов по идентификатору статьи.
     *
     * @param id идентификатор статьи
     * @return список объектов класса {@link ArticleCoauthor}
     */
    public List<ArticleCoauthor> getArticleCoauthorsByArticleId(final int id) {
        return jdbcTemplate.query("SELECT * FROM article_coauthors WHERE article_id = :articleId",
            Collections.singletonMap("articleId", id), articleCoauthorRowMapper);
    }

    /**
     * Возвращает статью {@link Article} по её идентификатору.
     *
     * @param id идентификатор статьи
     * @return {@link Article}
     */
    public Article getArticleByArticleId(final int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = :id",
            Collections.singletonMap("id", id), articleRowMapper);
    }


    /**
     * @param articleId id статьи
     * @return список рецензий
     */
    public List<Review> getReviewsByArticleId(final int articleId) {
        return jdbcTemplate.query("SELECT r.* "
                + "FROM review r "
                + "INNER JOIN article a "
                + "ON a.id = r.article_id "
                + "WHERE a.id = :articleId",
            Collections.singletonMap("articleId", articleId),
            reviewRowMapper);
    }

    /**
     * @param articleId id статьи
     * @return {@code true}, если статья опубликована, иначе - {@code false}
     */
    public boolean isPublished(final Integer articleId) {
        Integer id = jdbcTemplate.queryForObject("SELECT COUNT(*) "
                + "FROM article a "
                + "INNER JOIN issue_article ia "
                + "ON ia.article_id = a.id "
                + "WHERE a.id = :articleId",
            Collections.singletonMap("articleId", articleId),
            Integer.class);
        return id > 0;
    }

    /**
     * Метод для удаления статьи.
     *
     * @param articleId id статьи, которую нужно удалить
     */
    public void deleteArticleById(final Integer articleId) {
        jdbcTemplate.update("DELETE FROM article WHERE id = :articleId",
            Collections.singletonMap("articleId", articleId));
    }

    /**
     * Получение статей по id рубрики и журнала.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @return {@link List} of {@link Article}
     */
    public List<Article> getArticleByTopicAndPublishingId(final int topicId, final int publishingId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("publishingId", publishingId);
        params.put("topicId", topicId);
        return jdbcTemplate.query("SELECT * FROM article "
                + "WHERE topic_id = :topicId "
                + "AND publishing_id = :publishingId",
            params, articleRowMapper);
    }

    /**
     * Получение статей по id рубрики, журнала и автора.
     *
     * @param topicId      id рубрики
     * @param publishingId id журнала
     * @param authorId     id автора
     * @return {@link List} of {@link Article}
     */
    public List<Article> getArticleByTopicAndPublishingAndAuthorId(final int topicId,
                                                                   final int publishingId,
                                                                   final int authorId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("publishingId", publishingId);
        params.put("topicId", topicId);
        params.put("authorId", authorId);
        return jdbcTemplate.query("SELECT * FROM article "
                + "WHERE topic_id = :topicId "
                + "AND publishing_id = :publishingId "
                + "AND author_id = :authorId",
            params, articleRowMapper);
    }
}
