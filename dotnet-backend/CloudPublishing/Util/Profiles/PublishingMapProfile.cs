using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings.ViewModels;
using CloudPublishing.Util.PublishingValueResolvers;
using CloudPublishing.Business.Constants;

namespace CloudPublishing.Util
{
    public class PublishingMapProfile : Profile
    {
        public PublishingMapProfile()
        {
            CreateMap<PublishingDTO, PublishingTableViewModel>()
                .ForMember(dest => dest.Topics, opt => opt.ResolveUsing<TopicsNameToString>())
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => DataCorrelation.PublishingTypes[src.Type]));

            CreateMap<PublishingDTO, PublishingViewModel>();

            CreateMap<PublishingViewModel, PublishingDTO>()
                .ForMember(dest => dest.Topics, opt => opt.MapFrom(src => src.TopicsIds))
                .ForMember(dest => dest.Editors, opt => opt.MapFrom(src => src.EditorsIds))
                .ForMember(dest => dest.Journalists, opt => opt.MapFrom(src => src.JournalistsIds));

            CreateMap<TopicDTO, TopicViewModel>();

            CreateMap<EmployeeDTO, PublishingEmployeeViewModel>()
                .ForMember(dest => dest.Name, opt => opt.ResolveUsing<EmployeeShortName>());

            CreateMap<int, EmployeeDTO>()
                .ForMember(dest => dest.Id, opt => opt.MapFrom(src => src));

            CreateMap<int, TopicDTO>()
                .ForMember(dest => dest.Id, opt => opt.MapFrom(src => src));
        }
    }
}