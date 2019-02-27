using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Identity.Entities
{
    public class EmployeeRole : IRole<int>
    {
        public int Id { get; set; }
        public string Name { get; set; }
    }
}