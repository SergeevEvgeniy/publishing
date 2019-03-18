using System.Collections.Generic;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingCreateViewModel
    {
        public PublishingViewModel Publishing { get; set; }
        public IEnumerable<TopicViewModel> AvailableTopics { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableEmployees { get; set; }
    }
}