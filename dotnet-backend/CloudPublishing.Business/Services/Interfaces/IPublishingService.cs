using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IPublishingService
    {
        IEnumerable<PublishingDTO> GetPublishings();
        IEnumerable<TopicDTO> GetTopics();
        PublishingDTO GetPublishing(int id);
        IEnumerable<PublishingDTO> GetPublishingsByType(string type);
        void CreatePublishing(PublishingDTO publishing);
        void UpdatePublishing(PublishingDTO publishing);
        void DeletePublishing(int id);
        IEnumerable<EmployeeDTO> GetEditorsNotInPublishing(int publishingId);
        IEnumerable<EmployeeDTO> GetJournalistsNotInPublishing(int publishingId);
        IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId);
        IEnumerable<EmployeeDTO> GetJournalists();
        IEnumerable<EmployeeDTO> GetEditors();
        IEnumerable<TopicDTO> GetPublishingTopics(int id);
    }
}
