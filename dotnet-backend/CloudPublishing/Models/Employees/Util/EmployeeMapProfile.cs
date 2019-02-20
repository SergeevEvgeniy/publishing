using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Identity.Entities;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<EducationDTO, Education>();
            CreateMap<EmployeeDTO, Employee>();

            CreateMap<EmployeeDTO, EmployeeUser>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email));

            CreateMap<EmployeeUser, Employee>()
                .ForMember(dest => dest.Password, opt => opt.MapFrom(src => src.PasswordHash))
                .ForMember(dest => dest.Email, opt => opt.MapFrom(src => src.UserName));

            CreateMap<Employee, EmployeeUser>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email))
                .ForMember(dest => dest.PasswordHash, opt => opt.MapFrom(src => src.Password))
                .ForMember(dest => dest.Password, opt => opt.Ignore());

            CreateMap<EmployeeUser, EmployeeDTO>()
                .ForMember(dest => dest.Email, opt => opt.MapFrom(src => src.UserName));

            CreateMap<EmployeeUserCreateModel, EmployeeUser>();
        }
    }
}