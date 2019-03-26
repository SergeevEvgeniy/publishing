using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Employees.ApiModels;
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
        ///     Создает экземпляр класса, настраивая отображение <see cref="EmployeeDTO" />  на <see cref="EmployeeViewModel" />,
        ///     <see cref="EmployeeEditModel" />, <see cref="EmployeeCreateModel" /> и наоборот. Также
        ///     используется для отображения сущностей для WebApi
        /// </summary>
        public EmployeeMapProfile()
        {
            CreateMap<EmployeeDTO, EmployeeViewModel>()
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => src.Type.Title))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => src.Sex.Title));

            CreateMap<EmployeeDTO, EmployeeEditModel>()
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => src.Type.Title))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => src.Sex.Title));
            CreateMap<EmployeeEditModel, EmployeeDTO>()
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => new TypeDTO{Id = src.Type}))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => new SexDTO{Id = src.Sex}));

            CreateMap<EmployeeCreateModel, EmployeeDTO>()
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => new TypeDTO { Id = src.Type }))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => new SexDTO { Id = src.Sex }));

            CreateMap<EmployeeDTO, EmployeeData>()
                .ForMember(dest => dest.Education, opt => opt.MapFrom(src => src.Education.Title))
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => src.Type.Title))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => src.Sex.Title));
        }
    }
}