using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Business.Services
{
    /// <summary>
    /// Сервис работы с рецензиями
    /// </summary>
    class ReviewService : IReviewService
    {
        private IUnitOfWork db;
        private IMapper mapper;
        private IEmployeeService employeeService;
        private IPublishingService publishingService;
        private IArticleService articleService;

        /// <summary>
        /// Конструктор сервиса
        /// </summary>
        /// <param name="db">Класс репозиториев, созданный в соответствии с паттерном Unit of Work</param>
        /// <param name="employeeService">Сервис сотрудников издательства</param>
        /// <param name="publishingService">Сервис изданий</param>
        /// <param name="articleService">Сервис статей</param>
        public ReviewService(IUnitOfWork db, IEmployeeService employeeService, IPublishingService publishingService, IArticleService articleService)
        {
            this.db = db;
            this.employeeService = employeeService;
            this.publishingService = publishingService;
            this.articleService = articleService;

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewBusinessMapProfile())).CreateMapper();
        }

        /// <summary>
        /// Метод создания списка рецензий
        /// </summary>
        /// <param name="reviwerId">Id редактора</param>
        /// <returns>Список рецензий. Каждая рецензия содержит наименование издания, рубрики, имя автора,
        /// наименование статьи, id статьи и флаг одобрения рецензии</returns>
        public IEnumerable<DetailedReviewDTO> CreateDetailedReviewList(int reviwerId)
        {
            var reviews = db.Reviews.GetUserReviews(reviwerId);

            return reviews.Select(x =>
            {
                var article = articleService.GetArticleById(x.ArticleId);
                var author = employeeService.GetEmployeeById(article.AuthorId);
                var publishing = publishingService.GetPublishing(article.PublishingId);
                var topic = publishingService.GetTopic(article.TopicId);

                return new DetailedReviewDTO
                {
                    // Заполняем поля модели рецензии из ответа сервиса статей и объекта рецензии
                    ArticleId = article.Id,
                    ReviwerId = reviwerId,
                    Article = article.Title,
                    Approved = x.Approved,
                    Author = $"{author.FirstName} {author.MiddleName} {author.LastName}",
                    Publishing = publishing.Title,
                    Topic = topic.Name
                };
            });
        }

        /// <summary>
        /// Метод получения конкретной рецензии
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Рецензию</returns>
        public ReviewDTO GetReview(int articleId, int authorId)
        {
            return mapper.Map<ReviewDTO>(db.Reviews.Get(articleId, authorId));
        }

        /// <summary>
        /// Метод проверки допустимости публикации статьи
        /// </summary>
        /// <param name="id">Id статьи, которую требуется проверить</param>
        /// <returns>true, если во всех рецензиях статья одобрена, false, если есть рецензии, в которых статья не одобрена</returns>
        public IEnumerable<ReviewDTO> GetUnapprovedReviewsByArticleId(int id)
        {
            var reviews = db.Reviews.GetByArticleId(id);
            if(reviews.Count() == 0)
            {
                return null;
            }
            return mapper.Map<IEnumerable<ReviewDTO>>(reviews.Where(x => x.Approved == false));
        }

        /// <summary>
        /// Метод создания рецензии
        /// </summary>
        /// <param name="review">Объект рецензии</param>
        public void CreateReview(ReviewDTO review)
        {
            db.Reviews.Create(mapper.Map<Review>(review));
            db.Save();
        }

        /// <summary>
        /// Метод обновления рецензии
        /// </summary>
        /// <param name="review">Объект рецензии</param>
        public void UpdateReview(ReviewDTO review)
        {
            db.Reviews.Update(mapper.Map<Review>(review));
            db.Save();
        }

        /// <summary>
        /// Метод удаления рецензии
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <param name="authorId">Id редактора</param>
        public void DeleteReview(int articleId, int authorId)
        {
            db.Reviews.Delete(articleId, authorId);
            db.Save();
        }
    }
}
