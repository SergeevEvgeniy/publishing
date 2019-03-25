using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IPublishingService
    {
        IEnumerable<PublishingDTO> GetAllPublishings();
        IEnumerable<TopicDTO> GetAllTopics();
        PublishingDTO GetPublishing(int id);
        void CreatePublishing(PublishingDTO publishing);
        void UpdatePublishing(PublishingDTO publishing);
        void DeletePublishing(int id);
        IEnumerable<EmployeeDTO> GetEditorsNotInPublishing(int publishingId);
        IEnumerable<EmployeeDTO> GetJournalistsNotInPublishing(int publishingId);
        IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId);
        IEnumerable<EmployeeDTO> GetJournalistList();
        IEnumerable<EmployeeDTO> GetEditorList();
    }
}
