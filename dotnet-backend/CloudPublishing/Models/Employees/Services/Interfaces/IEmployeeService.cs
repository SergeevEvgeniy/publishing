using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.DTO;

namespace CloudPublishing.Models.Employees.Services.Interfaces
{
    public interface IEmployeeService : IDisposable
    {
        Task<IEnumerable<EmployeeDTO>> GetEmployeeList();

        Task<IEnumerable<EducationDTO>> GetEducationList();

        Task<EmployeeDTO> CreateEmployee(EmployeeDTO entity);

        Task<EmployeeDTO> GetEmployeeById(int id);

        Task EditEmployee(EmployeeDTO entity);
    }
}