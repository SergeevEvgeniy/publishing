using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeMapProfile : Profile
    {
        public EmployeeMapProfile()
        {
            CreateMap<EmployeeDTO, EmployeeEditModel>();

            CreateMap<EmployeeCreateModel, EmployeeDTO>();
        }
    }
}