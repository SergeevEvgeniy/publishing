using System;
using System.Collections.Generic;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Interfaces
{
    public interface IEmployeeRepository : IRepository<Employee>
    {
        IEnumerable<Employee> Find(Func<Employee, bool> predicate);

        IEnumerable<Education> GetEducationList();
    }
}