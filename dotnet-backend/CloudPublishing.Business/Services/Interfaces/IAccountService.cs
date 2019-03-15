using CloudPublishing.Business.DTO;
using System.Security.Claims;
using System.Threading.Tasks;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IAccountService  
    {
        Task<string> CreateAccountAsync(EmployeeDTO entity);

        Task<string> EditAccountAsync(EmployeeDTO entity);

        Task<string> DeleteAccountAsync(int? id);

        Task<ClaimsIdentity> AuthenticateUserAsync(EmployeeDTO entity);
    }
}