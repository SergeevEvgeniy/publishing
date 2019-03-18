using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Business.Util
{
    public class PublishingBusinessMapProfile : Profile
    {
        public PublishingBusinessMapProfile()
        {
            CreateMap<Publishing, PublishingDTO>().ReverseMap();
            CreateMap<Topic, TopicDTO>().ReverseMap();
        }
    }
}
