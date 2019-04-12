﻿using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IReviewService
    {
        IEnumerable<EmployeeDTO> GetAuthorList(int? publishingId, int? topicId);

        IEnumerable<ArticleDTO> GetArticleList(int? publishingId, int? topicId, int? authorId);

        IEnumerable<DetailedReviewDTO> CreateDetailedReviewList(int reviewerId);

        ReviewDTO GetReview(int articleId, int authorId);

        void CreateReview(ReviewDTO review);

        void UpdateReview(ReviewDTO review);

        void DeleteReview(int articleId, int authorId);

        string GetArticleContent(int id);
    }
}
