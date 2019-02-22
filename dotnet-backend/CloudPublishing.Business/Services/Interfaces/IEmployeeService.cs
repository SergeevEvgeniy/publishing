using System;
using System.Collections.Generic;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Results.Interfaces;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeService : IDisposable
    {
        IResult<IEnumerable<EmployeeDTO>> GetEmployeeList();

        IResult<IEnumerable<EducationDTO>> GetEducationList();

        IResult<EmployeeDTO> GetEmployeeById(int? id);

        IResult<int> CreateEmployee(EmployeeDTO entity);

        IResult<int> EditEmployee(EmployeeDTO entity);

        IResult<int> DeleteEmployee(int? id);
    }
}