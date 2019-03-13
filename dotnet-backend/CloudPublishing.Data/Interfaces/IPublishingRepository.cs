using CloudPublishing.Data.Entities;
using System.Collections.Generic;

namespace CloudPublishing.Data.Interfaces
{
    public interface IPublishingRepository
    {
        IEnumerable<Publishing> GetAll();

        Publishing Get(int id);

        void Create(Publishing item);

        void Update(Publishing item);

        void Delete(int id);
    }
}
