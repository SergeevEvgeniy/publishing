using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Reviews.ViewModels;

namespace CloudPublishing.Util
{
    public class ReviewMapProfile : Profile
    {
        public ReviewMapProfile()
        {
            CreateMap<DetailedReviewDTO, DetailedReviewModel>();
            CreateMap<ReviewModel, ReviewDTO>().ReverseMap();
            CreateMap<TopicDTO, TopicModel>();
            CreateMap<EmployeeDTO, AuthorModel>();
            CreateMap<ArticleDTO, ArticleModel>();
            CreateMap<PublishingDTO, PublishingModel>();
        }
    }
}