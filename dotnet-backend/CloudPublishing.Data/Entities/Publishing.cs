using System.Collections.Generic;

namespace CloudPublishing.Data.Entities
{
    public class Publishing
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public ICollection<Topic> Topics { get; set; }
        public ICollection<Employee> Employees { get; set; }
    }
}
