using System.Collections.Generic;

namespace CloudPublishing.Data.Entities
{
    public class Publishing
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public virtual ICollection<Topic> Topics { get; set; }
    }
}
