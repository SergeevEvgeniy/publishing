using CloudPublishing.Data.Entities;
using System.Collections.Generic;

namespace CloudPublishing.Data.Interfaces
{
    public interface ITopicRepository
    {
        IEnumerable<Topic> GetAll();
    }
}
