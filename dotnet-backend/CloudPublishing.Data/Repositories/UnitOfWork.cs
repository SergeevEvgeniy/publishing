using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;

        public IEmployeeRepository Employees { get; }

        public IReviewRepository Reviews { get; }

        public IPublishingRepository Publishings { get; }

        public ITopicRepository Topics { get; }

        public UnitOfWork(CloudPublishingContext context)
        {
            this.context = context;
            Employees = new EmployeeRepository(context);
            Reviews = new ReviewRepository(context);
            Publishings = new PublishingRepository(context);
            Topics = new TopicRepository(context);
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public int Save()
        {
            return context.SaveChanges();
        }
    }
}