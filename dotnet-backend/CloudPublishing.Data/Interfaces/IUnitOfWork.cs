using System;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Managers;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IEmployeeRepository Employees { get; }

        IReviewRepository Reviews { get; }

        IPublishingRepository Publishings { get; }

        ITopicRepository Topics { get; }

        IUserRepository Users { get; }

        int Save();
    }
}