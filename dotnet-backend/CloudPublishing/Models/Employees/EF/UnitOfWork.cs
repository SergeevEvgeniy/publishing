using System;
using CloudPublishing.Models.Employees.EF.Interfaces;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.EF
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly EmployeeContext context;
        private IAsyncRepository<Employee> employeeRepository;
        private IAsyncRepository<Education> educationRepository;

        public UnitOfWork(string connectionString)
        {
            context = new EmployeeContext(connectionString);
        }

        public IAsyncRepository<Employee> Employees => employeeRepository ?? new EmployeeRepository(context);
        public IAsyncRepository<Education> Education => educationRepository ?? new EducationRepository(context);

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