using System;
using System.Collections.Generic;
using CloudPublishing.Models.Employees.Entities;

namespace CloudPublishing.Models.Employees.Repositories.Interfaces
{
    public interface IEmployeeRepository : IRepository<Employee>, IDisposable
    {
        IEnumerable<Employee> GetEmployeeList();

        IEnumerable<Education> GetEducationList();

        int SaveChanges();
    }
}