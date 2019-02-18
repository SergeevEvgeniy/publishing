using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Identity;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<EducationDTO, Education>();
            CreateMap<EmployeeDTO, Employee>();

            CreateMap<EmployeeDTO, EmployeeIdentity>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email));

            CreateMap<EmployeeIdentity, Employee>()
                .ForMember(dest => dest.Password, opt => opt.MapFrom(src => src.PasswordHash))
                .ForMember(dest => dest.Email, opt => opt.MapFrom(src => src.UserName));

            CreateMap<Employee, EmployeeIdentity>()
                .ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email))
                .ForMember(dest => dest.PasswordHash, opt => opt.MapFrom(src => src.Password))
                .ForMember(dest => dest.Password, opt => opt.Ignore());
        }
    }
}