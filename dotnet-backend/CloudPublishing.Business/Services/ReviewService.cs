using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;

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
        private const string ArticleServiceURI = "http://10.99.33.221:8080/cloud_publishing_war/article";

        /// <summary>
        /// Конструктор сервиса
        /// </summary>
        /// <param name="db">Класс репозиториев, созданный в соответствии с паттерном Unit of Work</param>
        /// <param name="employeeService">Сервис сотрудников издательства</param>
        /// <param name="publishingService">Сервис изданий</param>
        public ReviewService(IUnitOfWork db, IEmployeeService employeeService, IPublishingService publishingService)
        {
            this.db = db;
            this.employeeService = employeeService;
            this.publishingService = publishingService;

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewBusinessMapProfile())).CreateMapper();
        }

        /// <summary>
        /// Метод получения списка авторов публиковавшихся в выбранной рубрике определенного журнала
        /// </summary>
        /// <param name="publishingId">Id издания</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Список авторов</returns>
        public IEnumerable<EmployeeDTO> GetAuthorList(int? publishingId, int? topicId)
        {
            var articleList = JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(
                ExecuteRequest($"{ArticleServiceURI}/articleByTopicAndPublishingId/{topicId}/{publishingId}"));

            return articleList.Select(x =>
            {
                return employeeService.GetEmployeeById(x.AuthorId);
            });
        }

        /// <summary>
        /// Метод получения списка статей автора, опубликованных в выбранной рубрике выбранного журнала
        /// </summary>
        /// <param name="publishingId">Id издания</param>
        /// <param name="topicId">Id рубрики</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Список статей</returns>
        public IEnumerable<ArticleDTO> GetArticleList(int? publishingId, int? topicId, int? authorId)
        {
            var articleList = JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(
                ExecuteRequest($"{ArticleServiceURI}/articleByTopicAndPublishingId/{topicId}/{publishingId}"));

            return articleList.Where(x => x.AuthorId == authorId);
        }

        /// <summary>
        /// Метод создания списка рецензий
        /// </summary>
        /// <param name="reviewerId">Id редактора</param>
        /// <returns>Список рецензий. Каждая рецензия содержит наименование издания, рубрики, имя автора,
        /// наименование статьи, id статьи и флаг одобрения рецензии</returns>
        public IEnumerable<DetailedReviewDTO> CreateDetailedReviewList(int reviewerId)
        {
            var reviews = db.Reviews.GetUserReviews(reviewerId);

            return reviews.Select(x =>
            {
                var article = JsonConvert.DeserializeObject<ArticleDTO>(ExecuteRequest($"{ArticleServiceURI}/articleById/{x.ArticleId}"));

                var author = employeeService.GetEmployeeById(article.AuthorId);
                var publishing = publishingService.GetPublishing(article.Id);
                var topic = publishingService.GetTopic(article.TopicId);

                return new DetailedReviewDTO
                {
                    // Заполняем поля модели рецензии из ответа сервиса статей и объекта рецензии
                    ArticleId = article.Id,
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

        private string ExecuteRequest(string uri)
        {
            var webRequest = WebRequest.Create(uri) as HttpWebRequest;

            webRequest.ContentType = "application/json";
            webRequest.UserAgent = "Nothing";

            using (var stream = webRequest.GetResponse().GetResponseStream())
            {
                using (var reader = new StreamReader(stream))
                {
                    return reader.ReadToEnd();
                }
            }
        }
    }
}
