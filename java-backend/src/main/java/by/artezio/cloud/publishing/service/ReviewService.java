package by.artezio.cloud.publishing.service;


import by.artezio.cloud.publishing.domain.Review;
import java.util.List;

/**
 * Сервис, содержащий бизнес-логику по обработке рецензий.
 * @author Igor Kuzmin
 */
public interface ReviewService {

    /**
     * Метод для получения всех рецензий статьи.
     * @param articleId id статьи
     * @return список {@link List} всех рецензий {@link Review} на статью
     * */
    List<Review> getReviewsByArticleId(final int articleId);

}
