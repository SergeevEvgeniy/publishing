using System;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.EF.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IRepository<Employee> Employees { get; }

        IRepository<Education> Education { get; }

        Task<int> SaveAsync();
    }
}