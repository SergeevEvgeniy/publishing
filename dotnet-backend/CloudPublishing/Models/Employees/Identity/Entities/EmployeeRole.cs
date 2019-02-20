using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Employees.Identity.Entities
{
    public class EmployeeRole : IRole<int>
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }
}