using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Entities
{
    public class Publishing
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public ICollection<Topic> Topics { get; set; }
    }
}
