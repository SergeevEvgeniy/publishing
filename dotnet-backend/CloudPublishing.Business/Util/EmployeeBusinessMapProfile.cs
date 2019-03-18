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
        ///     Создает экземпляр класса, настраивая отображение Employee и Education в EmployeeDTO и EducationDTO соответственно и
        ///     наоборот
        /// </summary>
        public EmployeeBusinessMapProfile()
        {
            CreateMap<Employee, EmployeeDTO>()
                .ForMember(dest => dest.Password, opt => opt.Ignore());
            CreateMap<EmployeeDTO, Employee>()
                .ForMember(dest => dest.Education, opt => opt.Ignore());
            CreateMap<Education, EducationDTO>();
        }
    }
}