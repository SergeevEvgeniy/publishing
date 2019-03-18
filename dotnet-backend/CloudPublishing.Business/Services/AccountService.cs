using System.Linq;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Util;

namespace CloudPublishing.Business.Services
{
    /// <inheritdoc />
    public class AccountService : IAccountService
    {
        private readonly IPasswordHasher hasher;
        private readonly IMapper mapper;
        private readonly IUnitOfWork unit;

        /// <summary>
        ///     Создает экземпляр класса сервиса, используя UnitOfWork, хэшер для пароля и маппер для отображения сущностей
        /// </summary>
        /// <param name="unit">Экземпляр UnitOfWork для работы с базой данных</param>
        /// <param name="hasher">Экземпляр хэшера для хеширования паролей</param>
        /// <param name="mapper">Экземпляр маппера для отображения сущностей</param>
        public AccountService(IUnitOfWork unit, IPasswordHasher hasher, IMapper mapper)
        {
            this.unit = unit;
            this.hasher = hasher;
            this.mapper = mapper;
        }

        /// <inheritdoc />
        public void CreateAccount(EmployeeDTO entity)
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
        public void EditAccount(EmployeeDTO entity)
        {
            var target = unit.Employees.Get(entity.Id);
            if (target == null)
            {
                throw new EntityNotFoundException("Пользователь не найден");
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
        public void DeleteAccount(int id)
        {
            var target = unit.Employees.Get(id);

            if (target == null)
            {
                throw new EntityNotFoundException("Пользователь не найден");
            }

            if (target.ChiefEditor)
            {
                throw new ChiefEditorRoleChangeException();
            }

            unit.Employees.Delete(id);
            unit.Save();
        }

        /// <inheritdoc />
        public EmployeeDTO AuthenticateUser(string email, string password)
        {
            var hashedPassword = hasher.HashPassword(password);

            var employee = unit.Employees.Find(x => x.Password == hashedPassword && x.Email == email).FirstOrDefault();
            return mapper.Map<Employee, EmployeeDTO>(employee);
        }
    }
}