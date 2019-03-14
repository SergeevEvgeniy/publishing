using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results;
using CloudPublishing.Business.Results.Interfaces;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;

namespace CloudPublishing.Business.Services
{
    public class AccountService : IAccountService
    {
        private readonly IUnitOfWork unit;
        private readonly IMapper mapper;

        public AccountService(IUnitOfWork unit)
        {
            this.unit = unit;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public async Task<IResult<string>> CreateAccountAsync(EmployeeDTO entity)
        {
            var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
            if (user == null) return new BadResult<string>("Пользователь не указан");

            try
            {
                var result = await unit.Users.CreateAsync(user);
                return !result.Succeeded
                    ? (IResult<string>)new BadResult<string>(result.Errors.Aggregate((resultError, error) =>
                       error + "\n"))
                    : new SuccessfulResult<string>("Пользователь " + user.UserName + " успешно создан");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e.Message, true);
            }
        }

        public async Task<IResult<string>> EditAccountAsync(EmployeeDTO entity)
        {
            if (entity == null) return new BadResult<string>("Отсутствует сущность");
            try
            {
                var target = await unit.Users.FindByIdAsync(entity.Id);
                if (target == null)
                {
                    return new BadResult<string>("Такого пользователя не существует");
                }
                if (target.ChiefEditor && !entity.ChiefEditor)
                {
                    return new BadResult<string>("Сначала необходимо указать другого главного редактора");
                }

                var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
                var result = await unit.Users.UpdateAsync(user);
                return !result.Succeeded
                    ? (IResult<string>)new BadResult<string>(result.Errors.Aggregate((resultError, error) =>
                       error + "\n"))
                    : new SuccessfulResult<string>("Данные пользователя" + user.UserName + " успешно обновлены");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e.Message, true);
            }
        }

        public async Task<IResult<string>> DeleteAccountAsync(int? id)
        {
            if (id == null) return new BadResult<string>("Отсутствует идентификатор сущности");
            try
            {
                var target = await unit.Users.FindByIdAsync(id.Value);
                if (target == null)
                {
                    return new BadResult<string>("Такого пользователя не существует");
                }
                if (target.ChiefEditor)
                {
                    return new BadResult<string>("Сначала необходимо указать другого главного редактора");
                }

                var result = await unit.Users.DeleteAsync(target);
                if (!result.Succeeded)
                {
                    return new BadResult<string>(result.Errors.Aggregate((resultError, error) => error + "\n"));
                }
                return new SuccessfulResult<string>("Пользователь " + target.UserName + "успешно удален");
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<string>(e.Message, true);
            }
        }

        public async Task<IResult<ClaimsIdentity>> AuthenticateUserAsync(EmployeeDTO entity)
        {
            if (entity == null) return new BadResult<ClaimsIdentity>("Отсутствует сущность");
            try
            {
                var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
                var claims = await unit.Users.AuthenticateAsync(user);
                return claims == null
                    ? (IResult<ClaimsIdentity>)new BadResult<ClaimsIdentity>(
                        "Ошибка аутентификации. Были введены неверные данные")
                    : new SuccessfulResult<ClaimsIdentity>(claims);
            }
            catch (InvalidOperationException e)
            {
                return new BadResult<ClaimsIdentity>(e.Message, true);
            }
        }
    }
}