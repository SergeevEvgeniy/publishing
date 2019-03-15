using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Business.Util
{
    public class EmployeeBusinessMapProfile : Profile
    {
        public EmployeeBusinessMapProfile()
        {
            CreateMap<Employee, EmployeeDTO>()
                .ForMember(dest => dest.Password, opt => opt.Ignore());
            CreateMap<EmployeeDTO, Employee>()
                .ForMember(dest => dest.Education, opt => opt.Ignore());
            CreateMap<Education, EducationDTO>();
        }
    }
}