using AutoMapper;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Models.Employees.RestApi;

namespace CloudPublishing.Util
{
    public class EmployeeApiMapProfile : Profile
    {
        public EmployeeApiMapProfile()
        {
            CreateMap<JournalistListFilterModel, JournalistListFilterDTO>();
        }
    }
}