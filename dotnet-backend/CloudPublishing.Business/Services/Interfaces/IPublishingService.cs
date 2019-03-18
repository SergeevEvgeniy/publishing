using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IPublishingService
    {
        IEnumerable<PublishingDTO> GetAllPublishings();
        IEnumerable<TopicDTO> GetAllTopics();
        IEnumerable<EmployeeDTO> GetAllEmployees();
        IEnumerable<EmployeeDTO> GetPublishingEmployees(int publishingId);
        PublishingDTO GetPublishing(int id);
        void CreatePublishing(PublishingDTO publishing);
        void UpdatePublishing(PublishingDTO publishing);
        void DeletePublishing(int id);
        IEnumerable<EmployeeDTO> GetEmployeesNotInPublishing(int publishingId);
        IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId);
    }
}
