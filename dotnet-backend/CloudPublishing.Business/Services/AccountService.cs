using System;
using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Util;

namespace CloudPublishing.Business.Services
{
    public class AccountService : IAccountService
    {
        private readonly IPasswordHasher hasher;
        private readonly IMapper mapper;
        private readonly IUnitOfWork unit;

        public AccountService(IUnitOfWork unit, IPasswordHasher hasher)
        {
            this.unit = unit;
            this.hasher = hasher;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public void CreateAccount(EmployeeDTO entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            var employee = mapper.Map<EmployeeDTO, Employee>(entity);
            employee.Password = hasher.HashPassword(entity.Password);
            unit.Employees.Create(employee);
            unit.Save();
        }

        public void EditAccount(EmployeeDTO entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            var target = unit.Employees.Get(entity.Id);
            if (target == null)
            {
                throw new NullReferenceException("Пользователь не найден");
            }


            if (target.ChiefEditor && !entity.ChiefEditor)
            {
                throw new ChiefEditorExistenceException();
            }

            if (entity.Password != null)
            {
                entity.Password = hasher.HashPassword(entity.Password);
            }

            unit.Employees.Update(mapper.Map<EmployeeDTO, Employee>(entity));
            unit.Save();
        }

        public void DeleteAccount(int? id)
        {
            if (id == null)
            {
                throw new ArgumentNullException(nameof(id));
            }

            var target = unit.Employees.Get(id.Value);

            if (target == null)
            {
                throw new NullReferenceException("Пользователь не найден");
            }

            if (target.ChiefEditor)
            {
                throw new ChiefEditorExistenceException();
            }

            unit.Employees.Delete(id.Value);
            unit.Save();
        }

        public EmployeeDTO AuthenticateUser(string email, string password)
        {
            var hashedPassword = hasher.HashPassword(password);

            var employee = unit.Employees.Find(x => x.Password == hashedPassword && x.Email == email).FirstOrDefault();
            return mapper.Map<Employee, EmployeeDTO>(employee);
        }
    }
}