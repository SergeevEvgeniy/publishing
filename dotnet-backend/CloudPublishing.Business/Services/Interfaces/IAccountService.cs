using System;
using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results.Interfaces;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IAccountService  
    {
        Task<IResult<string>> CreateAccountAsync(EmployeeDTO entity);

        Task<IResult<string>> EditAccountAsync(EmployeeDTO entity);

        Task<IResult<string>> DeleteAccountAsync(int? id);

        Task<IResult<ClaimsIdentity>> AuthenticateUserAsync(EmployeeDTO entity);
    }
}