using System.Collections.Generic;

namespace CloudPublishing.Models.Publishings.ViewModels
{
    public class PublishingCreateViewModel
    {
        public PublishingViewModel Publishing { get; set; }
        public IEnumerable<TopicViewModel> AvailableTopics { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableEditors { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableJournalists { get; set; }
    }
}