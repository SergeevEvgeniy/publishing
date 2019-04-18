package by.artezio.cloud.publishing.service.impl;

import by.artezio.cloud.publishing.dao.EmployeeDao;
import by.artezio.cloud.publishing.dao.ReviewDao;
import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.domain.Review;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;
import by.artezio.cloud.publishing.dto.ReviewShortInfo;
import by.artezio.cloud.publishing.service.ReviewService;
import by.artezio.cloud.publishing.service.converter.EmployeeToEmployeeShortInfoConverter;
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
    private EmployeeDao employeeDao;
    private ReviewToReviewShortInfoConverter reviewConverter;
    private EmployeeToEmployeeShortInfoConverter employeeConverter;

    /**
     * @param reviewDao         {@link ReviewDao}
     * @param employeeDao       {@link EmployeeDao}
     * @param reviewConverter   {@link ReviewToReviewShortInfoConverter}
     * @param employeeConverter {@link EmployeeToEmployeeShortInfoConverter}
     */
    @Autowired
    public FakeReviewService(final ReviewDao reviewDao,
                             final EmployeeDao employeeDao,
                             final ReviewToReviewShortInfoConverter reviewConverter,
                             final EmployeeToEmployeeShortInfoConverter employeeConverter) {
        this.reviewDao = reviewDao;
        this.employeeDao = employeeDao;
        this.reviewConverter = reviewConverter;
        this.employeeConverter = employeeConverter;
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
            Employee e = employeeDao.getEmployeeById(r.getReviewerId());
            EmployeeShortInfo esi = employeeConverter.convert(e);
            ReviewShortInfo rsi = reviewConverter.convert(r);
            rsi.setReviewerShortName(esi.getShortFullName());
            reviewShortInfos.add(rsi);
        }
        return reviewShortInfos;
    }

    @Override
    public ReviewShortInfo getReviewShortInfo(final int articleId, final int reviewerId) {
        return reviewConverter.convert(reviewDao.getReview(articleId, reviewerId));
    }
}
