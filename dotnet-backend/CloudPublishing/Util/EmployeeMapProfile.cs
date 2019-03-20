using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Employees;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Util
{
    /// <inheritdoc />
    /// <summary>
    ///     Профиль Automapper уровня представления для отображения сущностей сторудика на различного рода иодели
    /// </summary>
    public class EmployeeMapProfile : Profile
    {
        /// <summary>
        ///     Создает экземпляр класса, настраивая отображение <see cref="EmployeeDTO"/>  на <see cref="EmployeeViewModel"/>, <see cref="EmployeeEditModel"/>, <see cref="EmployeeCreateModel"/> и наоборот. Также
        ///     используется для отображения сущностей для WebApi
        /// </summary>
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