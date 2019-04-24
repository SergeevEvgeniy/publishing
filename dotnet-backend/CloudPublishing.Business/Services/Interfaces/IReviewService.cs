using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IReviewService
    {
        IEnumerable<DetailedReviewDTO> CreateDetailedReviewList(int reviewerId);

        ReviewDTO GetReview(int articleId, int authorId);

        void CreateReview(ReviewDTO review);

        void UpdateReview(ReviewDTO review);

        void DeleteReview(int articleId, int authorId);
    }
}
