using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Results.Interfaces;

namespace CloudPublishing.Models.Employees.Services.Interfaces
{
    public interface IEmployeeService : IDisposable
    {
        Task<IResult<IEnumerable<EmployeeDTO>>> GetEmployeeList();

        Task<IResult<IEnumerable<EducationDTO>>> GetEducationList();

        Task<IResult<EmployeeDTO>> CreateEmployee(EmployeeDTO entity);

        Task<IResult<EmployeeDTO>> GetEmployeeById(int id);

        Task<IResult<int>> EditEmployee(EmployeeDTO entity);

        Task<IResult<int>> DeleteEmployee(int id);
    }
}