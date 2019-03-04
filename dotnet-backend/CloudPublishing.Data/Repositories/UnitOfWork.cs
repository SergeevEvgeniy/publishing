using System.Net.Http;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;
        // private readonly HttpClient client;

        private readonly IEmployeeRepository employees = null;
        private readonly IReviewRepository reviews = null;
        private readonly IPublishingRepository publishings = null;
        private readonly ITopicRepository topics = null;

        public UnitOfWork(string connectionString)
        {
            context = new CloudPublishingContext(connectionString);
            // client = new HttpClient();
        }

        public void Dispose()
        {
            context?.Dispose();
            // client?.Dispose();
        }

        public IEmployeeRepository Employees => employees?? new EmployeeRepository(context);

        public IReviewRepository Reviews => reviews ?? new ReviewRepository(context);

        public IPublishingRepository Publishings => publishings ?? new PublishingRepository(context);

        public ITopicRepository Topics => topics ?? new TopicRepository(context);

        public int Save()
        {
            return context.SaveChanges();
        }
    }
}