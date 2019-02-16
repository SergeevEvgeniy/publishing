using System;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.EF.Interfaces;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.EF
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly EmployeeContext context;
        private IRepository<Employee> employeeRepository;
        private IRepository<Education> educationRepository;

        public UnitOfWork(string connectionString)
        {
            context = new EmployeeContext(connectionString);
        }

        public IRepository<Employee> Employees => employeeRepository ?? new EmployeeRepository(context);
        public IRepository<Education> Education => educationRepository ?? new EducationRepository(context);

        public Task<int> SaveAsync()
        {
            return context.SaveChangesAsync();
        }

        private bool disposed;

        public virtual void Dispose(bool disposing)
        {
            if (disposed) return;
            if (disposing)
            {
                context.Dispose();
            }
            disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}