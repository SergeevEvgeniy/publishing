using System.Collections.Generic;

namespace CloudPublishing.Models.Employees.ApiModels
{
    public class EmployeeFilter
    {
        public IEnumerable<int> IdList { get; set; }

        public string LastName { get; set; }

        public string Type { get; set; }
    }
}