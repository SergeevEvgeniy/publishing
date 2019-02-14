using AutoMapper;
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
        }
    }
}