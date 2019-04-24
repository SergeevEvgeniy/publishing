package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации объекта из {@link Review} в {@link ReviewShortInfo}.
 * <p>Сокращённое полное имя рецензента не заполняется!</p>
 *
 * @author Denis Shubin
 */
@Component
public class ReviewToReviewShortInfoConverter implements Converter<Review, ReviewShortInfo> {

    @Override
    public ReviewShortInfo convert(final Review review) {
        ReviewShortInfo shortInfo = new ReviewShortInfo();
        shortInfo.setArticleId(review.getArticleId());
        shortInfo.setReviewerId(review.getReviewerId());
        shortInfo.setContent(review.getContent());
        return shortInfo;
    }
}
