using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;
using CloudPublishing.Util.PublishingValueResolvers;

namespace CloudPublishing.Util
{
    public class PublishingMapProfile : Profile
    {
        public PublishingMapProfile()
        {
            CreateMap<PublishingDTO, PublishingTableViewModel>()
                .ForMember(dest => dest.Topics, opt => opt.ResolveUsing<TopicsNameToString>());
            CreateMap<PublishingDTO, PublishingViewModel>();
        }
    }
}