﻿using System.Collections.Generic;

namespace CloudPublishing.Models.Reviews.Repositories
{
    public interface IReviewRepository
    {
        void Create(Review item);

        IEnumerable<Review> GetAll();

        IEnumerable<Review> GetUserReviews(int reviewerId);

        Review Get(int articleId, int reviewerId);

        void Update(Review item);

        void Delete(int articleId, int reviewerId);

        void Save();
    }
}
