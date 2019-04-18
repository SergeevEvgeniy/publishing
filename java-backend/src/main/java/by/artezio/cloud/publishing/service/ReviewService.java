package by.artezio.cloud.publishing.service;


import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;

import java.util.List;

/**
 * Сервис, содержащий бизнес-логику по обработке рецензий.
 *
 * @author Igor Kuzmin
 */
public interface ReviewService {

    /**
     * Метод для получения всех рецензий статьи.
     *
     * @param articleId id статьи
     * @return список {@link List} всех рецензий {@link Review} на статью
     */
    List<Review> getReviewsByArticleId(final int articleId);

    /**
     * Возвращает список {@link ReviewShortInfo} для указанной статьи.
     *
     * @param articleId id статьи
     * @return {@link List} of {@link ReviewShortInfo}
     */
    List<ReviewShortInfo> getReviewShortInfoList(int articleId);

    /**
     * Получение рецензии по id статьи и рецензента.
     *
     * @param articleId  id статьи
     * @param reviewerId id рецензента
     * @return рецензия {@link Review}
     */
    ReviewShortInfo getReviewShortInfo(int articleId, int reviewerId);
}
