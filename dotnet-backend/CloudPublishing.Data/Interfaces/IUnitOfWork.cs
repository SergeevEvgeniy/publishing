using System;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IRepository<Employee> Employees { get; }

        IEducationRepository Educations { get; }

        void Save();
    }
}