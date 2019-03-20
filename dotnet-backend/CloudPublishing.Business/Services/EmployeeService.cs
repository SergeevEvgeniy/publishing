using System;
using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Enums;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Business.Services
{
    /// <inheritdoc />
    public class EmployeeService : IEmployeeService
    {
        private static readonly IDictionary<EmployeeType, string> Types;
        private static readonly IDictionary<Sex, string> Sexes;
        private readonly IMapper mapper;
        private readonly IUnitOfWork unitOfWork;

        static EmployeeService()
        {
            Types = new Dictionary<EmployeeType, string>
            {
                {EmployeeType.E, "Редактор"},
                {EmployeeType.J, "Журналист"}
            };
            Sexes = new Dictionary<Sex, string>
            {
                {Sex.M, "М"},
                {Sex.F, "Ж"}
            };
        }

        /// <summary>
        ///     Создает экземпляр класса из UnitOfWork и маппера для отображения сущностей
        /// </summary>
        /// <param name="unitOfWork">Экземпляр класса для работы с базой данных</param>
        /// <param name="mapper">Экземпляр маппера для отображения сущностей</param>
        public EmployeeService(IUnitOfWork unitOfWork, IMapper mapper)
        {
            this.unitOfWork = unitOfWork;
            this.mapper = mapper;
        }

        /// <inheritdoc />
        public void Dispose()
        {
            unitOfWork?.Dispose();
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEmployeeList()
        {
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unitOfWork.Employees.GetAll().Select(x =>
            {
                x.Type = Types[(EmployeeType) Enum.Parse(typeof(EmployeeType), x.Type)];
                x.Sex = Sexes[(Sex) Enum.Parse(typeof(Sex), x.Sex)];
                return x;
            }));
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEmployeeList(IEnumerable<int> idList, string lastName)
        {
            var list = unitOfWork.Employees.Find(x =>
                x.LastName.StartsWith(lastName ?? string.Empty) && idList.Contains(x.Id));
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list);
        }

        /// <inheritdoc />
        public IEnumerable<EducationDTO> GetEducationList()
        {
            return mapper.Map<IEnumerable<Education>, List<EducationDTO>>(unitOfWork.Employees.GetEducationList());
        }

        /// <inheritdoc />
        public EmployeeDTO GetEmployeeById(int id)
        {
            return mapper.Map<Employee, EmployeeDTO>(unitOfWork.Employees.Get(id));
        }

        /// <inheritdoc />
        public IDictionary<string, string> GetEmployeeTypes()
        {
            return Types.Select(x => new {key = x.Key.ToString(), x.Value}).ToDictionary(x => x.key, y => y.Value);
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetJournalistList()
        {
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(
                unitOfWork.Employees.Find(x => x.Type == EmployeeType.J.ToString()));
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEditorList()
        {
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(
                unitOfWork.Employees.Find(x => x.Type == EmployeeType.E.ToString()));
        }
    }
}