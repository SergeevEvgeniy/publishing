using System.Threading.Tasks;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Data.Identity.Managers
{
    public class EmployeeUserManager : UserManager<EmployeeUser, int>
    {
        public EmployeeUserManager(IUserStore<EmployeeUser, int> store) : base(store)
        {
            
        }

        public async Task<IdentityResult> UpdateAsync(EmployeeUser user, string newPassword)
        {
            if (!(Store is IUserPasswordStore<EmployeeUser, int> store))
            {
                return await Task.FromResult(
                    IdentityResult.Failed("Current UserStore doesn't implement IUserPasswordStore"));
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

        //public static EmployeeUserManager Create(IdentityFactoryOptions<EmployeeUserManager> options, IOwinContext context)
        //{
        //    var appDbContext = context.Get<CloudPublishingContext>();
        //    var appUserManager = new EmployeeUserManager(new EmployeeUserStore(appDbContext));

        //    return appUserManager;
        //}
    }
}