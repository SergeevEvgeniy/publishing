using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Data.Identity.Entities;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Interfaces
{
    public interface IUserRepository
    {
        Task<IdentityResult> CreateAsync(EmployeeUser user);

        Task<IdentityResult> UpdateAsync(EmployeeUser user);

        Task<EmployeeUser> FindByIdAsync(int id);

        Task<IdentityResult> DeleteAsync(EmployeeUser user);

        Task<ClaimsIdentity> AuthenticateAsync(EmployeeUser user);
    }
}