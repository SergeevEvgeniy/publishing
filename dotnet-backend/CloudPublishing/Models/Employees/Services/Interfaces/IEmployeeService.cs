using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Identity.Entities;
using CloudPublishing.Models.Employees.Results.Interfaces;
using CloudPublishing.Models.Shared.DTO;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Services.Interfaces
{
    public interface IEmployeeService : IOwinEmployeeService, IDisposable
    {
        Task<IResult<IEnumerable<EmployeeDTO>>> GetEmployeeList();

        Task<IResult<IEnumerable<EducationDTO>>> GetEducationList();

        Task<IResult<EmployeeDTO>> GetEmployeeById(int id);
    }
}