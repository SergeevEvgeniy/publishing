using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.EF.Interfaces
{
    public interface IUnitOfWork
    {
        IAsyncRepository<Employee> Employees { get; }

        IAsyncRepository<Education> Education { get; }
    }
}