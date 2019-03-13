using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingCreateViewModel
    {
        public PublishingViewModel Publishing { get; set; }
        public IEnumerable<TopicViewModel> TopicsNotAtPublishing { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> EmployeesNotInPublishing { get; set; }
        public IEnumerable<TopicViewModel> TopicsAtPublishing { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> EmployeesAtPublishing { get; set; }
    }
}