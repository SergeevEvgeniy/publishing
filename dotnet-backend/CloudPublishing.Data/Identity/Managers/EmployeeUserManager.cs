using CloudPublishing.Data.Identity.Entities;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Identity.Managers
{
    public class EmployeeUserManager : UserManager<EmployeeUser, int>
    {
        public EmployeeUserManager(IUserStore<EmployeeUser, int> store, IPasswordHasher passwordHasher) : base(store)
        {
            PasswordHasher = passwordHasher;
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
    }
}