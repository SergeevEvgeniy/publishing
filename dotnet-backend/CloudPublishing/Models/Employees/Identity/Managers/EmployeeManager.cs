using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Identity.Entities;
using CloudPublishing.Models.Employees.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Identity.Managers
{
    public class EmployeeManager : UserManager<Employee, int>
    {
        public EmployeeManager(IUserStore<Employee, int> store) : base(store)
        {
        }

        public static EmployeeManager Create(IdentityFactoryOptions<EmployeeManager> options, IOwinContext context)
        {
            var dbContext = context.Get<EmployeeContext>();
            var manager = new EmployeeManager(new EmployeeStore(dbContext));

            return manager;
        }

        public async Task<IdentityResult> UpdateAsync(Employee user, string newPassword)
        {
            if (!(Store is IUserPasswordStore<Employee, int> store))
            {
                return await Task.FromResult(IdentityResult.Failed("Current UserStore doesn't implement IUserPasswordStore"));
            }

            if (newPassword != null)
            {
                if (PasswordValidator != null)
                {
                    var passwordResult = await PasswordValidator.ValidateAsync(newPassword);
                    if (!passwordResult.Succeeded)
                        return passwordResult;
                }

                var newPasswordHash = PasswordHasher.HashPassword(newPassword);
                await store.SetPasswordHashAsync(user, newPasswordHash);
            }
            else
            {
                user.PasswordHash = await store.GetPasswordHashAsync(user);
            }
            
            await store.UpdateAsync(user);
            return await Task.FromResult(IdentityResult.Success);
        }
    }
}