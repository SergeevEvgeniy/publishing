using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingCreateModel
    {
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public IEnumerable<TopicViewModel> Topics { get; set; }
    }
}