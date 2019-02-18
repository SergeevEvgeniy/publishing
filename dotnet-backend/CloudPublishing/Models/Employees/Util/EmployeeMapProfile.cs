using AutoMapper;
using CloudPublishing.Models.Accounts.Entities;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Entities;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<Education, EducationDTO>();
            CreateMap<Employee, EmployeeDTO>();

            CreateMap<EducationDTO, Education>();
            CreateMap<EmployeeDTO, Employee>();

            CreateMap<EmployeeDTO, IdentityUser>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email));
        }
    }
}