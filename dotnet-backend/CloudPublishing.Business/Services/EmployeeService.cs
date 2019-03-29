using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using AutoMapper;
using CloudPublishing.Business.Constants;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Resources.Messages;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Util;

namespace CloudPublishing.Business.Services
{
    /// <inheritdoc />
    public class EmployeeService : IEmployeeService
    {
        private readonly IPasswordHasher hasher;
        private readonly IMapper mapper;
        private readonly IJournalistStatisticsService service;
        private readonly IUnitOfWork unit;

        /// <summary>
        ///     Создает экземпляр класса из реализаций <see cref="IUnitOfWork" />, маппера для отображения сущностей и хэшера для
        ///     паролей
        /// </summary>
        /// <param name="unit">Экземпляр класса для работы с базой данных</param>
        /// <param name="mapper">Экземпляр маппера для отображения сущностей</param>
        /// <param name="hasher">Экземпляр класса для хэширования</param>
        /// <param name="service">Экземпляр класса для получения статистики журналистов</param>
        public EmployeeService(IUnitOfWork unit, IMapper mapper, IPasswordHasher hasher,
            IJournalistStatisticsService service)
        {
            this.unit = unit;
            this.mapper = mapper;
            this.hasher = hasher;
            this.service = service;
        }

        /// <inheritdoc />
        public void Dispose()
        {
            unit?.Dispose();
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEmployees()
        {
            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unit.Employees.GetAll());
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEmployees(string type)
        {
            var list = unit.Employees.Find(x => x.Type == type);

            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list);
        }

        /// <inheritdoc />
        public IEnumerable<EmployeeDTO> GetEmployeesFromList(IEnumerable<int> idList, string lastName = null)
        {
            if (idList == null || !idList.Any())
            {
                return new List<EmployeeDTO>();
            }

            var list = unit.Employees.Find(x =>
                idList.Contains(x.Id) && x.LastName.StartsWith(lastName ?? string.Empty));

            return mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(list);
        }

        /// <inheritdoc />
        public IEnumerable<EducationDTO> GetEducationList()
        {
            return mapper.Map<IEnumerable<Education>, List<EducationDTO>>(unit.Employees.GetEducationList());
        }

        /// <inheritdoc />
        public EmployeeDTO GetEmployeeById(int id)
        {
            return mapper.Map<Employee, EmployeeDTO>(unit.Employees.Get(id));
        }

        /// <inheritdoc />
        public JournalistStatisticsDTO GetJournalistStatistics(int id)
        {
            var journalist = unit.Employees.Get(id);

            if (journalist == null || journalist.Type != EmployeeType.Journalist)
            {
                throw new EntityNotFoundException(Error.NotFoundJournalist);
            }

            var statistics = service.GetJournalistStatistics(id);

            if (statistics?.ArticleCountByPublishing == null || statistics.ArticleCountByTopics == null)
            {
                throw new HttpRequestException(Error.NoDataAquiredRemoteHost);
            }

            return mapper.Map(journalist, statistics);
        }

        /// <inheritdoc />
        public IDictionary<string, string> GetEmployeeTypes()
        {
            return DataCorrelation.EmployeeTypes;
        }

        /// <inheritdoc />
        public void CreateEmployee(EmployeeDTO entity)
        {
            if (entity.ChiefEditor)
            {
                var chief = unit.Employees.Find(x => x.ChiefEditor).FirstOrDefault();
                if (chief != null)
                {
                    chief.ChiefEditor = false;
                    unit.Employees.Update(chief);
                }
            }

            var employee = mapper.Map<EmployeeDTO, Employee>(entity);
            employee.Password = hasher.HashPassword(entity.Password);
            unit.Employees.Create(employee);
            unit.Save();
        }

        /// <inheritdoc />
        public void EditEmployee(EmployeeDTO entity)
        {
            var target = unit.Employees.Get(entity.Id);
            if (target == null)
            {
                throw new EntityNotFoundException(Error.NotFoundUser);
            }

            if (target.ChiefEditor && !entity.ChiefEditor)
            {
                throw new ChiefEditorRoleChangeException();
            }

            if (entity.ChiefEditor && !target.ChiefEditor)
            {
                var chief = unit.Employees.Find(x => x.ChiefEditor).FirstOrDefault();
                if (chief != null)
                {
                    chief.ChiefEditor = false;
                    unit.Employees.Update(chief);
                }
            }

            if (entity.Password != null)
            {
                entity.Password = hasher.HashPassword(entity.Password);
            }

            unit.Employees.Update(mapper.Map(entity, target));
            unit.Save();
        }

        /// <inheritdoc />
        public void DeleteEmployee(int id)
        {
            var target = unit.Employees.Get(id);

            if (target == null)
            {
                throw new EntityNotFoundException(Error.NotFoundUser);
            }

            if (target.ChiefEditor)
            {
                throw new ChiefEditorRoleChangeException();
            }

            unit.Employees.Delete(id);
            unit.Save();
        }

        /// <inheritdoc />
        public EmployeeDTO AuthenticateEmployee(string email, string password)
        {
            var hashedPassword = hasher.HashPassword(password);

            var employee = unit.Employees.Find(x => x.Password == hashedPassword && x.Email == email).FirstOrDefault();
            return mapper.Map<Employee, EmployeeDTO>(employee);
        }
    }
}