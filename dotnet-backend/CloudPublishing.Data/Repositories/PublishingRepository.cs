using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.Entity;

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
            
            return context.Publishings.AsNoTracking()
                .Include(p => p.Topics).AsNoTracking()
                .Include(p => p.PublishingEmployees).AsNoTracking()
                .FirstOrDefault(p => p.Id == id);
        }

        public IEnumerable<Publishing> GetAll()
        {
            return context.Publishings.AsNoTracking()
                .Include(p => p.Topics).AsNoTracking()
                .Include(p => p.PublishingEmployees).AsNoTracking()
                .ToList();
        }

        public void Create(Publishing publishing)
        {
            var topicIds = publishing.Topics.Select(t => t.Id);
            publishing.Topics = context.Topics.Where(t => topicIds.Contains(t.Id)).ToList();

            context.Publishings.Add(publishing);
        }

        public void Update(Publishing modifyPublishing)
        {
            Publishing publishingToUpdate = context.Publishings
                .Include(p => p.PublishingEmployees)
                .Include(p => p.Topics)
                .FirstOrDefault(x => x.Id == modifyPublishing.Id);

            if (publishingToUpdate == null)
            {
                throw new Exception($"Publishing with Id '{modifyPublishing.Id}' not found");
            }

            publishingToUpdate.Title = modifyPublishing.Title;
            publishingToUpdate.Type = modifyPublishing.Type;
            publishingToUpdate.Subjects = modifyPublishing.Subjects;
            publishingToUpdate.PublishingEmployees = modifyPublishing.PublishingEmployees;

            var topicIds = modifyPublishing.Topics.Select(t => t.Id);
            publishingToUpdate.Topics = context.Topics.Where(t => topicIds.Contains(t.Id)).ToList();
        }

        public void Delete(int id)
        {
            var publishing = context.Publishings.FirstOrDefault(x => x.Id == id);
            if (publishing == null)
            {
                throw new Exception($"Publishing with Id '{id}' not found");
            }
            context.Publishings.Remove(publishing);
        }
    }
}
