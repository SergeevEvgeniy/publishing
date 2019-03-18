using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Data.Repositories
{
    /// <summary>
    /// Репозиторий рецензий
    /// </summary>
    public class ReviewRepository : IReviewRepository
    {
        private CloudPublishingContext context;

        /// <summary>
        /// Конструктор класса
        /// </summary>
        /// <param name="context">Контекст, производный от DbContext для взаимодействия с БД</param>
        public ReviewRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        /// <summary>
        /// Метод создания рецензии
        /// </summary>
        /// <param name="item">Объект рецензии</param>
        public void Create(Review item)
        {
            context.Review.Add(item);
        }

        /// <summary>
        /// Метод получения списка рецензий
        /// </summary>
        /// <returns>Список рецензий</returns>
        public IEnumerable<Review> GetAll()
        {
            return context.Review;
        }

        /// <summary>
        /// Метод получения рецензий конкретного редактора
        /// </summary>
        /// <param name="reviewerId">Id редактора</param>
        /// <returns>Список рецензий</returns>
        public IEnumerable<Review> GetUserReviews(int reviewerId)
        {
            return context.Review.Where(x => x.ReviwerId == reviewerId);
        }

        /// <summary>
        /// Метод получения рецензии
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <param name="reviewerId">Id редактора</param>
        /// <returns>Рецензия</returns>
        public Review Get(int articleId, int reviewerId)
        {
            return context.Review.FirstOrDefault(x => x.ArticleId == articleId && x.ReviwerId == reviewerId);
        }

        /// <summary>
        /// Метод обновления рецензии
        /// </summary>
        /// <param name="item">Рецензия</param>
        public void Update(Review item)
        {
            context.Set<Review>().Attach(item);
        }

        /// <summary>
        /// Метод удаления рецензии
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <param name="reviewerId">Id рецензии</param>
        public void Delete(int articleId, int reviewerId)
        {
            Review review = Get(articleId, reviewerId);
            if (review != null)
            {
                context.Review.Remove(review);
            }
        }
    }
}