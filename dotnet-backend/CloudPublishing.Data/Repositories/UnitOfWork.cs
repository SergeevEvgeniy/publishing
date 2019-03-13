using System.Net.Http;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Managers;
using CloudPublishing.Data.Identity.Stores;
using CloudPublishing.Data.Interfaces;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;
        private readonly EmployeeUserManager manager;

        private readonly IEmployeeRepository employees = null;
        private readonly IReviewRepository reviews = null;
        private readonly IPublishingRepository publishings = null;
        private readonly ITopicRepository topics = null;
        private readonly IUserRepository users = null;

        public UnitOfWork(CloudPublishingContext context, EmployeeUserManager manager)
        {
            this.context = context;
            this.manager = manager;
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public IEmployeeRepository Employees => employees?? new EmployeeRepository(context);

        public IReviewRepository Reviews => reviews ?? new ReviewRepository(context);

        public IPublishingRepository Publishings => publishings ?? new PublishingRepository(context);

        public ITopicRepository Topics => topics ?? new TopicRepository(context);

        public IUserRepository Users => users ?? new UserRepository(manager);

        public int Save()
        {
            return context.SaveChanges();
        }
    }
}