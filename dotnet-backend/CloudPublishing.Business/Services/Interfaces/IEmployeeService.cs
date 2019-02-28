using System;
using System.Collections.Generic;
using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeService : IDisposable
    {
        IResult<IEnumerable<EmployeeDTO>> GetEmployeeList();

        IResult<IEnumerable<EducationDTO>> GetEducationList();

        IResult<EmployeeDTO> GetEmployeeById(int? id);

        Task<IResult<string>> CreateEmployeeAsync(EmployeeDTO entity);

        Task<IResult<string>> EditEmployeeAsync(EmployeeDTO entity);

        Task<IResult<string>> DeleteEmployeeAsync(int? id);

        Task<IResult<ClaimsIdentity>> AuthenticateUserAsync(EmployeeDTO entity);
    }
}