using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Repositories
{
    public class PublishingRepository : IRepository<Publishing>
    {
        private readonly CloudPublishingContext context;

        public PublishingRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public Publishing Get(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Publishing> GetAll()
        {
            throw new NotImplementedException();
        }

        public void Create(Publishing item)
        {
            throw new NotImplementedException();
        }

        public void Update(Publishing item)
        {
            throw new NotImplementedException();
        }

        public void Delete(int id)
        {
            throw new NotImplementedException();
        }
    }
}
