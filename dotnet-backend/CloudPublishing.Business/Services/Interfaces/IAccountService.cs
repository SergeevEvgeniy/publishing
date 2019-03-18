using System.Collections.Generic;
using CloudPublishing.Business.DTO;
using System.Security.Claims;
using System.Threading.Tasks;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IAccountService  
    {
        void CreateAccount(EmployeeDTO entity);

        void EditAccount(EmployeeDTO entity);

        void DeleteAccount(int id);

        EmployeeDTO AuthenticateUser(string email, string password);
    }
}