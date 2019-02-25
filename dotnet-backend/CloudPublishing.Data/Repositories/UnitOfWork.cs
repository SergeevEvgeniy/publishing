using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;

        private readonly IEmployeeRepository employees = null;
        private readonly IReviewRepository reviews = null;

        public UnitOfWork(string connectionString)
        {
            context = new CloudPublishingContext(connectionString);
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public IEmployeeRepository Employees => employees?? new EmployeeRepository(context);

        public IReviewRepository Reviews => reviews ?? new ReviewRepository(context);

        public int Save()
        {
            return context.SaveChanges();
        }
    }
}