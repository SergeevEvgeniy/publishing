using AutoMapper;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Business.Util
{
    public class EmployeeBusinessApiMapProfile : Profile
    {
        public EmployeeBusinessApiMapProfile()
        {
            CreateMap<Employee, JournalistStatisticsDTO>();

            CreateMap<Employee, JournalistDTO>();
        }
    }
}