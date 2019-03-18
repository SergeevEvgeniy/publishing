package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.ReviewDao;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Фейковый сервис, содержащий бизнес-логику по обработке рецензий.
 * @author Igor Kuzmin
 * */

@Service
public class FakeReviewService implements ReviewService {

    private ReviewDao reviewDao;

    /**
     * Конструктор с параметрами.
     * @param reviewDao {@link ReviewDao}
     * */
    @Autowired
    public FakeReviewService(final ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    /**
     * @param articleId id статьи
     * @return спсиок всех рецензий на данную статью
     * */
    @Override
    public List<Review> getReviewsByArticleId(final int articleId) {
        return reviewDao.getReviewsByArticleId(articleId);
    }

}
