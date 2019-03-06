using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Reviews.ViewModels;

namespace CloudPublishing.Util
{
    public class ReviewMapProfile : Profile
    {
        public ReviewMapProfile()
        {
            CreateMap<DetailedReviewDTO, DetailedReviewVM>();
            CreateMap<ReviewVM, ReviewDTO>();
            CreateMap<ReviewDTO, ReviewVM>();
            CreateMap<PublishingDTO, ShortPublishingModel>();
            CreateMap<TopicDTO, TopicModel>();
            CreateMap<EmployeeDTO, AuthorModel>();
            CreateMap<ArticleDTO, ArticleModel>();
        }
    }
}