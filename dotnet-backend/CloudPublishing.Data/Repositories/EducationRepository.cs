using System.Collections.Generic;
using System.Linq;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class EducationRepository : IEducationRepository
    {
        private readonly CloudPublishingContext context;

        public EducationRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public IEnumerable<Education> GetAll()
        {
            return context.Educations.ToList();
        }
    }
}