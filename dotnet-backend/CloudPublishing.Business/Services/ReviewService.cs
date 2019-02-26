using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services
{
    class ReviewService
    {
        private IUnitOfWork db;

        public ReviewService(IUnitOfWork db)
        {
            this.db = db;
        }

        IEnumerable<PublishingDTO> GetPublishingList()
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        IEnumerable<TopicDTO> GetTopicList(int publishingId)
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        IEnumerable<EmployeeDTO> GetAuthorList(int publishingId, int topicId)
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        IEnumerable<ArticleDTO> GetArticleList(int publishingId, int topicId, int authorId)
        {
            // Заглушка. Будет реализован запрос
            return null;
        }

        ReviewDTO GetReview(int articleId, int authorId)
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<Review, ReviewDTO>()).CreateMapper();
            return mapper.Map<Review, ReviewDTO>(db.Reviews.Get(articleId, authorId));
        }

        void UpdateReview(ReviewDTO review)
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<ReviewDTO, Review>()).CreateMapper();
            db.Reviews.Update(mapper.Map<ReviewDTO, Review>(review));
        }

        void DeleteReview(int articleId, int authorId)
        {
            db.Reviews.Delete(articleId, authorId);
        }
    }
}
