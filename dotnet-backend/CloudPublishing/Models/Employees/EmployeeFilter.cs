using System.Collections.Generic;

namespace CloudPublishing.Models.Employees
{
    public class EmployeeFilter
    {
        public IEnumerable<int> IdList { get; set; }

        public string LastName { get; set; }
    }
}