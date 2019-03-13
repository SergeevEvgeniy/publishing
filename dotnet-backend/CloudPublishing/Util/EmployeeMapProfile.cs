using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Employees;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<EmployeeDTO, EmployeeEditModel>();
            CreateMap<EmployeeEditModel, EmployeeDTO>();

            CreateMap<EmployeeCreateModel, EmployeeDTO>();

            CreateMap<EmployeeDTO, EmployeeData>()
                .ForMember(dest => dest.Education, opt => opt.MapFrom(src => src.Education.Title));
        }
    }
}