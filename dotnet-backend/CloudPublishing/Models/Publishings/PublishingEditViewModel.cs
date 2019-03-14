using System.Collections.Generic;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingEditViewModel
    {
        public PublishingViewModel Publishing { get; set; }
        public IEnumerable<TopicViewModel> TopicsAtPublishing { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> EmployeesAtPublishing { get; set; }
        public IEnumerable<TopicViewModel> AvailableTopics { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableEmployees { get; set; }
    }
}