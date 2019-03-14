package by.artezio.cloud.publishing.dao;

import by.artezio.cloud.publishing.domain.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;


/**
 * Класс для взаимодействия с таблицей review.
 * @author Igor Kuzmin
 * */
@Repository
public class ReviewDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Review> reviewByArticleIdRowMapper = (rs, i) -> {
        Review review = new Review();
        review.setArticleId(rs.getInt("article_id"));
        review.setReviewerId(rs.getInt("reviwer_id"));
        review.setContent(rs.getString("content"));
        review.setApproved(rs.getBoolean("approved"));
        return review;
    };

    /**
     * Конструктор с параметром.
     * @param jdbcTemplate Объект, доступа к базе данных.
     * */
    public ReviewDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получение списка рецензий по идентификатору статьи.
     * @param articleId id статьи
     * @return список {@link List} всех рецензий {@link Review} на статью
     * */
    public List<Review> getReviewsByArticleId(final int articleId) {
        return jdbcTemplate.query("select * from review where article_id = :articleId",
            Collections.singletonMap("articleId", articleId),
            reviewByArticleIdRowMapper);
    }

}
