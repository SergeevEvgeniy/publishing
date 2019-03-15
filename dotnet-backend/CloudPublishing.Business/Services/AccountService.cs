using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Business.Services
{
    public class AccountService : IAccountService
    {
        private readonly IMapper mapper;
        private readonly IUnitOfWork unit;

        public AccountService(IUnitOfWork unit)
        {
            this.unit = unit;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeBusinessMapProfile())).CreateMapper();
        }

        public async Task<string> CreateAccountAsync(EmployeeDTO entity)
        {
            var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
            var result = await unit.Users.CreateAsync(user);
            return !result.Succeeded
                ? result.Errors.Aggregate((resultError, error) => error + "\n")
                : "Данные пользователя" + user.UserName + " успешно обновлены";
        }

        public async Task<string> EditAccountAsync(EmployeeDTO entity)
        {
            var target = await unit.Users.FindByIdAsync(entity.Id);
            if (target == null)
            {
                return "Такого пользователя не существует";
            }


            if (target.ChiefEditor && !entity.ChiefEditor)
            {
                return "Сначала необходимо указать другого главного редактора";
            }

            var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
            var result = await unit.Users.UpdateAsync(user);
            return !result.Succeeded
                ? result.Errors.Aggregate((resultError, error) => error + "\n")
                : "Данные пользователя" + user.UserName + " успешно обновлены";
        }

        public async Task<string> DeleteAccountAsync(int? id)
        {
            var target = await unit.Users.FindByIdAsync(id.Value);
            if (target == null)
            {
                return "Такого пользователя не существует";
            }

            if (target.ChiefEditor)
            {
                return "Сначала необходимо указать другого главного редактора";
            }

            var result = await unit.Users.DeleteAsync(target);
            if (!result.Succeeded)
            {
                return result.Errors.Aggregate((resultError, error) => error + "\n");
            }

            return "Пользователь " + target.UserName + "успешно удален";
        }

        public async Task<ClaimsIdentity> AuthenticateUserAsync(EmployeeDTO entity)
        {
            var user = mapper.Map<EmployeeDTO, EmployeeUser>(entity);
            return await unit.Users.AuthenticateAsync(user);
        }
    }
}