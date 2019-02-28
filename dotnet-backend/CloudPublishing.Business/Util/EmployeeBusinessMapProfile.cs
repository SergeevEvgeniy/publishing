using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;

namespace CloudPublishing.Business.Util
{
    public class EmployeeBusinessMapProfile : Profile
    {
        public EmployeeBusinessMapProfile()
        {
            CreateMap<Employee, EmployeeDTO>()
                .ForMember(dest => dest.Password, opt => opt.Ignore());
            CreateMap<Education, EducationDTO>();

            CreateMap<EmployeeDTO, EmployeeUser>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email));
        }
    }
}