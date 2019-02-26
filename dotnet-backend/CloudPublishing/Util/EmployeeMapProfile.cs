using AutoMapper;
using CloudPublishing.Business.DTO;
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
        }
    }
}