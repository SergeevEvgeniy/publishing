using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IPublishingService
    {
        IEnumerable<PublishingDTO> GetAllPublishings();
        IEnumerable<TopicDTO> GetAllTopics();
        
    }
}
