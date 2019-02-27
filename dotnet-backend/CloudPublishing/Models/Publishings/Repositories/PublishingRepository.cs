using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CloudPublishing.Models.Publishings.Entities;

namespace CloudPublishing.Models.Publishings.Repositories
{
    public class PublishingRepository : IPublishingRepository
    {
        private PublishingContext context = new PublishingContext();
        public IEnumerable<Publishing> Publishings
        {
            get { return context.Publishings; }
        }
    }
}