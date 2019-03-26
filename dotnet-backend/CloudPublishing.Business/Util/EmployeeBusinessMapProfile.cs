using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Business.Util
{
    /// <inheritdoc />
    /// <summary>
    ///     Профиль Automapper для отображения сущностей сторудика и типа образования
    /// </summary>
    public class EmployeeBusinessMapProfile : Profile
    {
        /// <summary>
        ///     Создает экземпляр класса, настраивая отображение <see cref="Employee" /> и <see cref="Education" /> на
        ///     <see cref="EmployeeDTO" /> и <see cref="EducationDTO" /> соответственно и наоборот
        /// </summary>
        public EmployeeBusinessMapProfile()
        {
            CreateMap<Employee, EmployeeDTO>()
                .ForMember(dest => dest.Password, opt => opt.Ignore())
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => new TypeDTO {Id = src.Type}))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => new SexDTO {Id = src.Sex}));
            CreateMap<EmployeeDTO, Employee>()
                .ForMember(dest => dest.Education, opt => opt.Ignore())
                .ForMember(dest => dest.Type, opt => opt.MapFrom(src => src.Type.Id))
                .ForMember(dest => dest.Sex, opt => opt.MapFrom(src => src.Sex.Id));
            CreateMap<Education, EducationDTO>();
        }
    }
}