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

        public ReviewService(IUnitOfWork db)
        {
            this.db = db;
        }

        public IEnumerable<PublishingDTO> GetPublishingList()
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        public IEnumerable<TopicDTO> GetTopicList(int publishingId)
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        public IEnumerable<EmployeeDTO> GetAuthorList(int publishingId, int topicId)
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        public IEnumerable<ArticleDTO> GetArticleList(int publishingId, int topicId, int authorId)
        {
            // Заглушка. Будет реализован запрос
            return null;
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
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<Review, ReviewDTO>()).CreateMapper();
            return mapper.Map<Review, ReviewDTO>(db.Reviews.Get(articleId, authorId));
        }

        public void CreateReview(ReviewDTO review)
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<ReviewDTO, Review>()).CreateMapper();
            db.Reviews.Create(mapper.Map<ReviewDTO, Review>(review));
        }

        public void UpdateReview(ReviewDTO review)
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<ReviewDTO, Review>()).CreateMapper();
            db.Reviews.Update(mapper.Map<ReviewDTO, Review>(review));
        }

        public void DeleteReview(int articleId, int authorId)
        {
            db.Reviews.Delete(articleId, authorId);
        }
    }
}
