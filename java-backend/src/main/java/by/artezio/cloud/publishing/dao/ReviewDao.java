package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Класс для взаимодействия с таблицей review.
 *
 * @author Igor Kuzmin
 */
@Repository
public class ReviewDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Review> reviewRowMapper = (rs, i) -> {
        Review review = new Review();
        review.setArticleId(rs.getInt("article_id"));
        review.setReviewerId(rs.getInt("reviwer_id"));
        review.setContent(rs.getString("content"));
        review.setApproved(rs.getBoolean("approved"));
        return review;
    };

    /**
     * Конструктор с параметром.
     *
     * @param jdbcTemplate Объект, доступа к базе данных.
     */
    public ReviewDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получение списка рецензий по идентификатору статьи.
     *
     * @param articleId id статьи
     * @return список {@link List} всех рецензий {@link Review} на статью
     */
    public List<Review> getReviewsByArticleId(final int articleId) {
        return jdbcTemplate.query("select * from review where article_id = :articleId",
            Collections.singletonMap("articleId", articleId),
            reviewRowMapper);
    }

    /**
     * @param articleId  id статьи
     * @param reviewerId id рецензента
     * @return {@link Review}
     */
    public Review getReview(final int articleId, final int reviewerId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("articleId", articleId);
        params.put("reviewerId", reviewerId);
        return jdbcTemplate.queryForObject("SELECT * FROM review "
                + "WHERE article_id = :articleId "
                + "AND reviwer_id = :reviewerId",
            params, reviewRowMapper);
    }

    /**
     * Удаление рецензий, связанных с указанной статьёй.
     *
     * @param articleId id статьи
     */
    public void deleteReviewByArticleid(final int articleId) {
        jdbcTemplate.update("DELETE FROM review WHERE article_id = :articleId",
            Collections.singletonMap("articleId", articleId));
    }
}
