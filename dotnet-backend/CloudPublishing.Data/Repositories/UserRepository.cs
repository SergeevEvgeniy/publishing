using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Managers;
using CloudPublishing.Data.Identity.Stores;
using CloudPublishing.Data.Interfaces;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly EmployeeUserManager manager;

        public UserRepository(CloudPublishingContext context)
        {
            manager = new EmployeeUserManager(new EmployeeUserStore(context));
        }

        public Task<IdentityResult> CreateAsync(EmployeeUser user)
        {
            return manager.CreateAsync(user, user.Password);
        }

        public Task<IdentityResult> UpdateAsync(EmployeeUser user)
        {
            return manager.UpdateAsync(user);
        }

        public Task<EmployeeUser> FindByIdAsync(int id)
        {
            return manager.FindByIdAsync(id);
        }

        public Task<IdentityResult> DeleteAsync(EmployeeUser user)
        {
            return manager.DeleteAsync(user);
        }

        public async Task<ClaimsIdentity> AuthenticateAsync(EmployeeUser user)
        {
            var employee = await manager.FindAsync(user.UserName, user.Password);
            return employee != null ? await manager.CreateIdentityAsync(employee, DefaultAuthenticationTypes.ApplicationCookie) : null;
        }
    }
}