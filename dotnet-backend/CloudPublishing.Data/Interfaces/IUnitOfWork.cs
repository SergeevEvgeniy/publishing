﻿using System;

namespace CloudPublishing.Data.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IEmployeeRepository Employees { get; }

        IReviewRepository Reviews { get; }

        IPublishingRepository Publishings { get; }

        ITopicRepository Topics { get; }

        int Save();
    }
}