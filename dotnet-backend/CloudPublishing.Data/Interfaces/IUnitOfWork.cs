using System;

namespace CloudPublishing.Data.Interfaces
{
    /// <inheritdoc />
    /// <summary>
    /// Объединяет все репозитории и предоставляет к ним доступ посредством свойств только для чтения
    /// </summary>
    public interface IUnitOfWork : IDisposable
    {
        /// <summary>
        /// Репозиторий пользователей
        /// </summary>
        IEmployeeRepository Employees { get; }

        /// <summary>
        /// Репозиторий рецензий
        /// </summary>
        IReviewRepository Reviews { get; }

        /// <summary>
        /// Репозиторий изданий
        /// </summary>
        IPublishingRepository Publishings { get; }

        /// <summary>
        /// Репозиторий тематик изданий
        /// </summary>
        ITopicRepository Topics { get; }

        /// <summary>
        /// сохранить изменения одной транзакцией
        /// </summary>
        /// <returns>Количество измененных строк базы данных</returns>
        int Save();
    }
}