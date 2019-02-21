using AutoMapper;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Models.Shared.DTO;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<EducationDTO, Education>();
            CreateMap<EmployeeDTO, Employee>().ForMember(dest => dest.UserName, opt => opt.MapFrom(src => src.Email));
            CreateMap<Employee, EmployeeDTO>().ForMember(dest => dest.Email, opt => opt.MapFrom(src => src.UserName));

            CreateMap<EmployeeDTO, EmployeeEditModel>()
                .ForMember(dest => dest.Password, opt => opt.Ignore());

            CreateMap<EmployeeCreateModel, EmployeeDTO>();
            CreateMap<EmployeeEditModel, EmployeeDTO>();
        }
    }
}