using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;
using CloudPublishing.Util.PublishingValueResolvers;
using System.Collections.Generic;

namespace CloudPublishing.Util
{
    public class PublishingMapProfile : Profile
    {
        private Dictionary<string, string> publishingType = new Dictionary<string, string>
        {
            { "M", "Журнал" },
            { "P", "Газета" }
        };
        public PublishingMapProfile()
        {
            CreateMap<PublishingDTO, PublishingTableViewModel>()
                .ForMember(dest => dest.Topics, opt => opt.ResolveUsing<TopicsNameToString>())
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => publishingType[src.Type]));

            CreateMap<PublishingDTO, PublishingViewModel>();

            CreateMap<PublishingViewModel, PublishingDTO>()
                .ForMember(dest => dest.Employees, opt => opt.MapFrom(src => src.EmployeesId))
                .ForMember(dest => dest.Topics, opt => opt.MapFrom(src => src.TopicsId));

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