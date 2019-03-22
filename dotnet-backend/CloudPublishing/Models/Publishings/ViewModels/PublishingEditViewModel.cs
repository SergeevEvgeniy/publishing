using System.Collections.Generic;

namespace CloudPublishing.Models.Publishings.ViewModels
{
    public class PublishingEditViewModel
    {
        public PublishingViewModel Publishing { get; set; }
        public IEnumerable<TopicViewModel> TopicsAtPublishing { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> EditorsAtPublishing { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> JournalistsAtPublishing { get; set; }
        public IEnumerable<TopicViewModel> AvailableTopics { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableEditors { get; set; }
        public IEnumerable<PublishingEmployeeViewModel> AvailableJournalists { get; set; }
    }
}