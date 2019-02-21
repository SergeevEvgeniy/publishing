using System.Collections.Generic;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Interfaces
{
    public interface IEducationRepository
    {
        IEnumerable<Education> GetAll();
    }
}