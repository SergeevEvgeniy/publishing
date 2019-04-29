package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.ReviewDao;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;
import by.artezio.cloud.publishing.service.ReviewService;
import by.artezio.cloud.publishing.service.converter.ReviewToReviewShortInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Фейковый сервис, содержащий бизнес-логику по обработке рецензий.
 *
 * @author Igor Kuzmin
 */

@Service
public class FakeReviewService implements ReviewService {

    private ReviewDao reviewDao;
    private ReviewToReviewShortInfoConverter reviewConverter;

    /**
     * @param reviewDao         {@link ReviewDao}
     * @param reviewConverter   {@link ReviewToReviewShortInfoConverter}
     */
    @Autowired
    public FakeReviewService(final ReviewDao reviewDao,
                             final ReviewToReviewShortInfoConverter reviewConverter) {
        this.reviewDao = reviewDao;
        this.reviewConverter = reviewConverter;
    }

    /**
     * @param articleId id статьи
     * @return спсиок всех рецензий на данную статью
     */
    @Override
    public List<Review> getReviewsByArticleId(final int articleId) {
        return reviewDao.getReviewsByArticleId(articleId);
    }

    @Override
    public List<ReviewShortInfo> getReviewShortInfoList(final int articleId) {
        List<Review> reviews = reviewDao.getReviewsByArticleId(articleId);
        List<ReviewShortInfo> reviewShortInfos = new ArrayList<>(reviews.size());
        for (Review r : reviews) {
            ReviewShortInfo rsi = reviewConverter.convert(r);
            reviewShortInfos.add(rsi);
        }
        return reviewShortInfos;
    }

    @Override
    public ReviewShortInfo getReviewShortInfo(final int articleId, final int reviewerId) {
        return reviewConverter.convert(reviewDao.getReview(articleId, reviewerId));
    }
}
