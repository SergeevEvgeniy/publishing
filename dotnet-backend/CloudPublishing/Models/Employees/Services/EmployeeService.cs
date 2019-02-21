using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Identity.Managers;
using CloudPublishing.Models.Employees.Repositories.Interfaces;
using CloudPublishing.Models.Employees.Results;
using CloudPublishing.Models.Employees.Results.Interfaces;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.Util;
using CloudPublishing.Models.Shared.DTO;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Services
{
    public class EmployeeService : IEmployeeService
    {
        private readonly IMapper mapper;
        private readonly IEmployeeRepository repository;


        public EmployeeService(IEmployeeRepository repository)
        {
            this.repository = repository;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            repository?.Dispose();
        }

        public async Task<IResult<string>> CreateEmployee(IOwinContext context, EmployeeDTO employee)
        {
            var manager = context.GetUserManager<EmployeeManager>();
            var user = mapper.Map<EmployeeDTO, Employee>(employee);
            var result = await manager.CreateAsync(user, user.Password);
            return GenerateResultFromIdentity("Создание пользователя отменено по следующим причинам:",
                "Пользователь" + employee.Email + " успешно создан", result);
        }

        public async Task<IResult<string>> EditEmployee(IOwinContext context, EmployeeDTO employee)
        {
            var manager = context.GetUserManager<EmployeeManager>();
            var user = mapper.Map<EmployeeDTO, Employee>(employee);
            var result = await manager.UpdateAsync(user, user.Password);
            return GenerateResultFromIdentity("Редактирование данных пользователя отменено по следующим причинам:",
                "Пользователь" + employee.Email + " успешно изменен", result);
        }

        public async Task<IResult<IEnumerable<EmployeeDTO>>> GetEmployeeList()
        {
            try
            {
                var list = await Task.Run(() =>
                    mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(repository.GetEmployeeList()));
                return new SuccessfulResult<IEnumerable<EmployeeDTO>>(list);
            }
            catch (DbException e)
            {
                return new BadResult<IEnumerable<EmployeeDTO>>(e);
            }
        }

        public async Task<IResult<IEnumerable<EducationDTO>>> GetEducationList()
        {
            try
            {
                var list = await Task.Run(() =>
                    mapper.Map<IEnumerable<Education>, List<EducationDTO>>(repository.GetEducationList()));
                return new SuccessfulResult<IEnumerable<EducationDTO>>(list);
            }
            catch (DbException e)
            {
                return new BadResult<IEnumerable<EducationDTO>>(e);
            }
        }

        public async Task<IResult<EmployeeDTO>> GetEmployeeById(int id)
        {
            try
            {
                var employee = await Task.Run(() =>
                    new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                        .Map<Employee, EmployeeDTO>(repository.Find(id)));
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (DbException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }

        private static IResult<string> GenerateResultFromIdentity(string errorHeader, string successHeader,
            IdentityResult result)
        {
            return result.Succeeded
                ? (IResult<string>) new SuccessfulResult<string>(successHeader)
                : new BadResult<string>(errorHeader + "\n" + result.Errors.Aggregate((aggregate, item) =>
                                            aggregate + "\n" + item));
        }
    }
}