using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;

        public UnitOfWork(string connectionString)
        {
            context = new CloudPublishingContext(connectionString);
            Employees = new EmployeeRepository(context);
            Reviews = new ReviewRepository(context);
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public IEmployeeRepository Employees { get; }

        public IReviewRepository Reviews { get; }

        public int Save()
        {
            return context.SaveChanges();
        }
    }
}