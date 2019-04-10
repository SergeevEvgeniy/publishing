using CloudPublishing.Data.Entities;
using System.Collections.Generic;

namespace CloudPublishing.Data.Interfaces
{
    public interface ITopicRepository
    {
        Topic GetTopic(int id);
        IEnumerable<Topic> GetAll();
    }
}
