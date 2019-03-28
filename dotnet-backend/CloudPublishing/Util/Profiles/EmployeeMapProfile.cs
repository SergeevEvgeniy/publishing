using AutoMapper;
using CloudPublishing.Business.Constants;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Employees.ApiModels;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Util.Profiles
{
    /// <inheritdoc />
    /// <summary>
    ///     Профиль Automapper уровня представления для отображения сущностей сторудика на различного рода иодели
    /// </summary>
    public class EmployeeMapProfile : Profile
    {
        /// <summary>
        ///     Создает экземпляр класса, настраивая отображение <see cref="EmployeeDTO" />  на <see cref="EmployeeViewModel" />,
        ///     <see cref="EmployeeEditModel" />, <see cref="EmployeeCreateModel" /> и наоборот. Также
        ///     используется для отображения сущностей для WebApi
        /// </summary>
        public EmployeeMapProfile()
        {
            CreateMap<EmployeeDTO, EmployeeItemModel>()
                .ForMember(dest => dest.FullName,
                    opt => opt.MapFrom(src => $"{src.LastName} {src.FirstName[0]}. {src.MiddleName[0]}."));

            CreateMap<EmployeeDTO, EmployeeViewModel>()
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => DataCorrelation.EmployeeSexes[src.Sex]))
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => DataCorrelation.EmployeeTypes[src.Type]));

            CreateMap<EmployeeDTO, EmployeeEditModel>();

            CreateMap<EmployeeCreateModel, EmployeeDTO>();

            CreateMap<EmployeeDTO, EmployeeData>()
                .ForMember(dest => dest.Education, opt => opt.MapFrom(src => src.Education.Title));
        }
    }
}