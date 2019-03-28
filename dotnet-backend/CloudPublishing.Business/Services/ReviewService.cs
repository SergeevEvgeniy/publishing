using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services
{
    /// <summary>
    /// Сервис работы с рецензиями
    /// </summary>
    class ReviewService : IReviewService
    {
        private IUnitOfWork db;
        private IMapper mapper;

        /// <summary>
        /// Конструктор сервиса
        /// </summary>
        /// <param name="db">Класс репозиториев, созданный в соответствии с паттерном Unit of Work</param>
        public ReviewService(IUnitOfWork db)
        {
            this.db = db;

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewBusinessMapProfile())).CreateMapper();
        }

        /// <summary>
        /// Метод получения списка изданий
        /// </summary>
        /// <returns>Возвращает список изданий</returns>
        /// Создан для тестирования
        public IEnumerable<PublishingDTO> GetPublishingList()
        {
            return new List<PublishingDTO>()
            {
                new PublishingDTO(){Id = 1, Title = "Садоводство"},
                new PublishingDTO(){Id = 2, Title = "Цивилизация"}
            };
        }

        /// <summary>
        /// Метод получения списка рубрик издания
        /// </summary>
        /// <param name="publishingId">Id издания</param>
        /// <returns>Список рубрикиздания</returns>
        /// Создан для тестирования
        public IEnumerable<TopicDTO> GetTopicList(int? publishingId)
        {
            return new List<TopicDTO>()
            {
                new TopicDTO(){Id = 1, Name = "Урожай"},
                new TopicDTO(){Id = 2, Name = "Дача"}
            };
        }

        /// <summary>
        /// Метод получения списка авторов публиковавшихся в выбранной рубрике определенного журнала
        /// </summary>
        /// <param name="publishingId">Id издания</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Список авторов</returns>
        /// Создан для тестирования
        public IEnumerable<EmployeeDTO> GetAuthorList(int? publishingId, int? topicId)
        {
            // Заглушка. Будет реализован запрос
            return new List<EmployeeDTO>()
            {
                new EmployeeDTO(){Id = 1, LastName = "Коваленко"},
                new EmployeeDTO(){Id = 2, LastName = "Петров"}
            };
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
            // Заглушка. Будет реализован запрос
            return new List<ArticleDTO>()
            {
                new ArticleDTO(){Id = 1, Title = "Садим тыкву, садим вместе"},
                new ArticleDTO(){Id = 2, Title = "Тайны великих пирамид"}
            };
        }

        /// <summary>
        /// Метод создания списка рецензий
        /// </summary>
        /// <param name="reviewerId">Id редактора</param>
        /// <returns>Список рецензий. Каждая рецензия содержит наименование издания, рубрики, имя автора,
        /// наименование статьи, id статьи и флаг одобрения рецензии</returns>
        public IEnumerable<DetailedReviewDTO> CreateDetailedReviewList(int reviewerId)
        {
            // Заглушка для тестирования. Будет последовательный сбор информации с разных сервисов
            return new List<DetailedReviewDTO>
            {
                new DetailedReviewDTO
                {
                    Publishing = "Садоводство",
                    Topic = "Урожай",
                    Author = "Коваленко М.О.",
                    Article = "Садим тыкву, садим вместе",
                    ArticleId = 1,
                    Approved = false
                },
                new DetailedReviewDTO
                {
                    Publishing = "Садоводство",
                    Topic = "Урожай",
                    Author = "Петров П.П.",
                    Article = "Огурцы - кладовая витаминов",
                    ArticleId = 2,
                    Approved = false
                },
                new DetailedReviewDTO
                {
                    Publishing = "Садоводство",
                    Topic = "Дача",
                    Author = "Коваленко М.О.",
                    Article = "Готовим на природе",
                    ArticleId = 3,
                    Approved = true
                }
            };
        }

        /// <summary>
        /// Метод получения конкретной рецензии
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Рецензию</returns>
        public ReviewDTO GetReview(int articleId, int authorId)
        {
            /*return mapper.Map<Review, ReviewDTO>(db.Reviews.Get(articleId, authorId));*/

            // Заглушка для тестирования
            return new ReviewDTO()
            {
                ReviwerId = 1,
                ArticleId = 1,
                Content = "Review content",
                Approved = false
            };
        }

        /// <summary>
        /// Метод создания рецензии
        /// </summary>
        /// <param name="review">Объект рецензии</param>
        public void CreateReview(ReviewDTO review)
        {
            db.Reviews.Create(mapper.Map<ReviewDTO, Review>(review));
            db.Save();
        }

        /// <summary>
        /// Метод обновления рецензии
        /// </summary>
        /// <param name="review">Объект рецензии</param>
        public void UpdateReview(ReviewDTO review)
        {
            db.Reviews.Update(mapper.Map<ReviewDTO, Review>(review));
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
