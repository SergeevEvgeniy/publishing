using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Entities
{
    public class Topic
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public IEnumerable<Publishing> Publishings{ get; set; }
    }
}
