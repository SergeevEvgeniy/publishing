using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Identity.Entities;
using CloudPublishing.Models.Employees.Results.Interfaces;
using CloudPublishing.Models.Shared.DTO;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Services.Interfaces
{
    public interface IOwinEmployeeService
    {
        Task<IResult<string>> CreateEmployee(IOwinContext context, EmployeeDTO employee);

        Task<IResult<string>> EditEmployee(IOwinContext context, EmployeeDTO employee);
    }
}