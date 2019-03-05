using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Data.Repositories
{
    public class PublishingRepository : IPublishingRepository
    {
        private readonly CloudPublishingContext context;

        public PublishingRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public Publishing Get(int id)
        {
            return context.Publishings.AsNoTracking().FirstOrDefault(x => x.Id == id);
        }

        public IEnumerable<Publishing> GetAll()
        {
            return context.Publishings.AsNoTracking().Include("Topics").ToList();
        }

        public void Create(Publishing publishing)
        {
            context.Publishings.Add(publishing);
            context.SaveChanges();
        }

        public void Update(Publishing publishing)
        {
            context.Entry(publishing).State = System.Data.Entity.EntityState.Modified;
            context.SaveChanges();
        }

        public void Delete(int id)
        {
            var publishing = context.Publishings.FirstOrDefault(x => x.Id == id);
            if (publishing != null)
            {
                context.Publishings.Remove(publishing);
                context.SaveChanges();
            }
        }
    }
}
