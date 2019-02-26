using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    interface IReviewService
    {
        IEnumerable<PublishingDTO> GetPublishingList();

        IEnumerable<TopicDTO> GetTopicList(int publishingId);

        IEnumerable<EmployeeDTO> GetAuthorList(int publishingId, int topicId);

        IEnumerable<ArticleDTO> GetArticleList(int publishingId, int topicId, int authorId);

        ReviewDTO GetReview(int articleId, int authorId);

        void UpdateReview(ReviewDTO review);

        void DeleteReview(int articleId, int authorId);
    }
}
