using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Business.Util
{
    public class EmployeeBusinessMapProfile : Profile
    {
        public EmployeeBusinessMapProfile()
        {
            CreateMap<Employee, EmployeeDTO>();
            CreateMap<Education, EducationDTO>();
        }
    }
}