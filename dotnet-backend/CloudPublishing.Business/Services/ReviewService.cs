using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services
{
    class ReviewService : IReviewService
    {
        private IUnitOfWork db;
        private IMapper mapper;

        public ReviewService(IUnitOfWork db)
        {
            this.db = db;

            MapperConfiguration mapperConfig = new MapperConfiguration(cfg =>
            {
                cfg.CreateMap<Review, ReviewDTO>();
                cfg.CreateMap<ReviewDTO, Review>();
            });
            mapper = mapperConfig.CreateMapper();
        }

        public IEnumerable<PublishingDTO> GetPublishingList()
        {
            // Заглушка. Будет реализован запрос
            return new List<PublishingDTO>()
            {
                new PublishingDTO(){Id = 1, Title = "Садоводство"},
                new PublishingDTO(){Id = 2, Title = "Цивилизация"}
            };
        }

        public IEnumerable<TopicDTO> GetTopicList(int? publishingId)
        {
            // Заглушка. Будет реализован запрос
            return new List<TopicDTO>()
            {
                new TopicDTO(){Id = 1, Name = "Урожай"},
                new TopicDTO(){Id = 2, Name = "Дача"}
            };
        }

        public IEnumerable<EmployeeDTO> GetAuthorList(int? publishingId, int? topicId)
        {
            // Заглушка. Будет реализован запрос
            return new List<EmployeeDTO>()
            {
                new EmployeeDTO(){Id = 1, LastName = "Коваленко"},
                new EmployeeDTO(){Id = 2, LastName = "Петров"}
            };
        }

        public IEnumerable<ArticleDTO> GetArticleList(int? publishingId, int? topicId, int? authorId)
        {
            // Заглушка. Будет реализован запрос
            return new List<ArticleDTO>()
            {
                new ArticleDTO(){Id = 1, Title = "Садим тыкву, садим вместе"},
                new ArticleDTO(){Id = 2, Title = "Тайны великих пирамид"}
            };
        }

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

        public ReviewDTO GetReview(int articleId, int authorId)
        {
            /*var mapper = new MapperConfiguration(cfg => cfg.CreateMap<Review, ReviewDTO>()).CreateMapper();
            return mapper.Map<Review, ReviewDTO>(db.Reviews.Get(articleId, authorId));*/
            // Заглушка для тестирования
            return new ReviewDTO()
            {
                ReviwerId = 1,
                ArticleId = 1,
                Content = "Review content",
                Approved = false
            };
        }

        public void CreateReview(ReviewDTO review)
        {
            db.Reviews.Create(mapper.Map<ReviewDTO, Review>(review));
        }

        public void UpdateReview(ReviewDTO review)
        {
            db.Reviews.Update(mapper.Map<ReviewDTO, Review>(review));
        }

        public void DeleteReview(int articleId, int authorId)
        {
            db.Reviews.Delete(articleId, authorId);
        }
    }
}
