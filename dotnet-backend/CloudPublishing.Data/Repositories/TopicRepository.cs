using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Data.Repositories
{
    public class TopicRepository : ITopicRepository
    {
        private readonly CloudPublishingContext context;

        public TopicRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public IEnumerable<Topic> GetAll()
        {
            return context.Topics.ToList();
        }
    }
}
