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

            CreateMap<TopicDTO, TopicViewModel>();

            CreateMap<EmployeeDTO, PublishingEmployeeViewModel>()
                .ForMember(dest => dest.Name, opt => opt.ResolveUsing<EmployeeShortName>());

            CreateMap<PublishingViewModel, PublishingDTO>()
                .ForMember(dest => dest.Employees, opt => opt.MapFrom(src => src.EmployeesId))
                .ForMember(dest => dest.Topics, opt => opt.MapFrom(src => src.TopicsId));

            CreateMap<int, EmployeeDTO>()
                .ForMember(dest => dest.Id, opt => opt.MapFrom(src => src));

            CreateMap<int, TopicDTO>()
                .ForMember(dest => dest.Id, opt => opt.MapFrom(src => src));
        }
    }
}