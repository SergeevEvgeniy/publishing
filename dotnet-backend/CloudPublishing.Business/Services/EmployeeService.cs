using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results;
using CloudPublishing.Business.Results.Interfaces;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Interfaces;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Business.Services
{
    public class EmployeeService : IEmployeeService
    {
        private readonly IMapper mapper;
        private readonly IUnitOfWork unitOfWork;

        public EmployeeService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            unitOfWork?.Dispose();
        }

        public IResult<IEnumerable<EmployeeDTO>> GetEmployeeList()
        {
            try
            {
                var list = mapper.Map<IEnumerable<Employee>, List<EmployeeDTO>>(unitOfWork.Employees.GetAll());
                return new SuccessfulResult<IEnumerable<EmployeeDTO>>(list);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<EmployeeDTO>>(e);
            }
        }

        public IResult<IEnumerable<EducationDTO>> GetEducationList()
        {
            try
            {
                var list = mapper.Map<IEnumerable<Education>, List<EducationDTO>>(
                    unitOfWork.Employees.GetEducationList());
                return new SuccessfulResult<IEnumerable<EducationDTO>>(list);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<IEnumerable<EducationDTO>>(e);
            }
        }

        public IResult<EmployeeDTO> GetEmployeeById(int? id)
        {
            if (id == null) return new BadResult<EmployeeDTO>("Отсутствует идентификатор");
            try
            {
                var employee = mapper.Map<Employee, EmployeeDTO>(unitOfWork.Employees.Get(id.Value));
                return new SuccessfulResult<EmployeeDTO>(employee);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<EmployeeDTO>(e);
            }
        }

        public async Task<IResult<string>> CreateEmployeeAsync(EmployeeDTO entity)
        {
            var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
            if (user == null) return new BadResult<string>("Пользователь не указан");

            try
            {
                var result = await unitOfWork.Users.CreateAsync(user);
                return !result.Succeeded
                    ? (IResult<string>) new BadResult<string>(result.Errors.Aggregate((resultError, error) =>
                        error + "\n"))
                    : new SuccessfulResult<string>("Пользователь " + user.UserName + " успешно создан");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e);
            }
        }

        public async Task<IResult<string>> EditEmployeeAsync(EmployeeDTO entity)
        {
            if (entity == null) return new BadResult<string>("Отсутствует сущность");
            try
            {
                var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
                var result = await unitOfWork.Users.UpdateAsync(user);
                return !result.Succeeded
                    ? (IResult<string>) new BadResult<string>(result.Errors.Aggregate((resultError, error) =>
                        error + "\n"))
                    : new SuccessfulResult<string>("Данные пользователя" + user.UserName + " успешно обновлены");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e);
            }
        }

        public async Task<IResult<string>> DeleteEmployeeAsync(int? id)
        {
            if (id == null) return new BadResult<string>("Отсутствует идентификатор сущности");
            try
            {
                var target = await unitOfWork.Users.FindByIdAsync(id.Value);
                if (target == null)
                {
                    return new BadResult<string>("Такого пользователя не существует");
                }
                if (target.ChiefEditor)
                {
                    return new BadResult<string>("Сначала необходимо указать другого главного редактора");
                }

                var result = await unitOfWork.Users.DeleteAsync(target);
                if (!result.Succeeded)
                {
                    return new BadResult<string>(result.Errors.Aggregate((resultError, error) => error + "\n"));
                }
                return new SuccessfulResult<string>("Пользователь " + target.UserName + "успешно удален");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e);
            }
        }

        public async Task<IResult<ClaimsIdentity>> AuthenticateUserAsync(EmployeeDTO entity)
        {
            if (entity == null) return new BadResult<ClaimsIdentity>("Отсутствует сущность");
            try
            {
                var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
                var claims = await unitOfWork.Users.AuthenticateAsync(user);
                return claims == null
                    ? (IResult<ClaimsIdentity>) new BadResult<ClaimsIdentity>(
                        "Ошибка аутентификации. Были введены неверные данные")
                    : new SuccessfulResult<ClaimsIdentity>(claims);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<ClaimsIdentity>(e);
            }
        }
    }
}