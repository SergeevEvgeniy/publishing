using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;

namespace CloudPublishing.Business.Util
{
    class PublishingBusinessMapProfile : Profile
    {
        public PublishingBusinessMapProfile()
        {
            CreateMap<Publishing, PublishingDTO>();

            CreateMap<PublishingDTO, Publishing>();

            CreateMap<Topic, TopicDTO>();

            CreateMap<TopicDTO, Topic>();

        }
    }
}
