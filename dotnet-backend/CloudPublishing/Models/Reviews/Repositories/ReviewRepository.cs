﻿using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Models.Reviews.Repositories
{
    public class ReviewRepository : IReviewRepository
    {
        private ReviewContext context;

        public ReviewRepository()
        {
            context = new ReviewContext();
        }

        public void Create(Review item)
        {
            context.Review.Add(item);
        }

        public IEnumerable<Review> GetAll()
        {
            return context.Review;
        }

        public IEnumerable<Review> GetUserReviews(int reviewerId)
        {
            return context.Review.Where(x => x.Reviwer_id == reviewerId);
        }

        public Review Get(int articleId, int reviewerId)
        {
            return context.Review.FirstOrDefault(x => x.Article_id == articleId && x.Reviwer_id == reviewerId);
        }

        public void Update(Review item)
        {
            context.Set<Review>().Attach(item);
        }

        public void Delete(int articleId, int reviewerId)
        {
            Review review = Get(articleId, reviewerId);
            if (review != null)
            {
                context.Review.Remove(review);
            }
        }

        public void Save()
        {
            context.SaveChanges();
        }
    }
}