using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings.Entities
{
    public class Publishing
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public char Type { get; set; }
        public string Subjects { get; set; }
        public ICollection<Topic> Topics { get; set; }
        public Publishing()
        {
            Topics = new List<Topic>();
        }
    }
}