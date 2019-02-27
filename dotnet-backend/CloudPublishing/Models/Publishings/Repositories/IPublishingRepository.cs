using CloudPublishing.Models.Publishings.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings.Repositories
{
    public interface IPublishingRepository
    {
        IEnumerable<Publishing> Publishings { get;  }
    }
}