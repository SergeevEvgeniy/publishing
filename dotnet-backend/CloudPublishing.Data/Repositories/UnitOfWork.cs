using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    /// <inheritdoc />
    public class UnitOfWork : IUnitOfWork
    {
        private readonly CloudPublishingContext context;

        /// <summary>
        ///     Создает экземпляр класса используя контекст работы с базой данных для объединения репозиториев
        /// </summary>
        /// <param name="context">Контекст работы с базой данных</param>
        public UnitOfWork(CloudPublishingContext context)
        {
            this.context = context;
            Employees = new EmployeeRepository(context);
            Reviews = new ReviewRepository(context);
            Publishings = new PublishingRepository(context);
            Topics = new TopicRepository(context);
        }

        /// <inheritdoc />
        public IEmployeeRepository Employees { get; }

        /// <inheritdoc />
        public IReviewRepository Reviews { get; }

        /// <inheritdoc />
        public IPublishingRepository Publishings { get; }

        /// <inheritdoc />
        public ITopicRepository Topics { get; }

        /// <inheritdoc />
        public void Dispose()
        {
            context?.Dispose();
        }

        /// <inheritdoc />
        public int Save()
        {
            return context.SaveChanges();
        }
    }
}