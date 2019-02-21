using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
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
            Educations = new EducationRepository(context);
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public IRepository<Employee> Employees { get; }

        public IEducationRepository Educations { get; }

        public void Save()
        {
            context.SaveChanges();
        }
    }
}