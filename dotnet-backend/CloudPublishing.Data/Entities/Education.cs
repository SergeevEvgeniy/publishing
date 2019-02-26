using System.Collections.Generic;

namespace CloudPublishing.Data.Entities
{
    public class Education
    {
        public Education()
        {
            Employees = new HashSet<Employee>();
        }

        public int Id { get; set; }

        public string Title { get; set; }

        public ICollection<Employee> Employees { get; set; }
    }
}