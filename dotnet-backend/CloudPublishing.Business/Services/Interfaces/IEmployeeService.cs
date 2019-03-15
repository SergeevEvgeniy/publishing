using CloudPublishing.Business.DTO;
using System;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IEmployeeService : IDisposable
    {
        IEnumerable<EmployeeDTO> GetEmployeeList();

        IEnumerable<EmployeeDTO> GetEmployeeList(IEnumerable<int> idList, string lastName);

        IEnumerable<EducationDTO> GetEducationList();

        EmployeeDTO GetEmployeeById(int? id);
    }
}