using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings.Entities
{
    public class Topic
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public ICollection<Publishing> Publishings { get; set; }
        public Topic()
        {
            Publishings = new List<Publishing>();
        }
    }
}