using System.Data.Entity;
using CloudPublishing.Data.EF.EntityConfigurations;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF
{
    /// <inheritdoc />
    /// <summary>
    ///     Представляет собой контекст работы с базой данных издательства
    /// </summary>
    public class CloudPublishingContext : DbContext
    {
        /// <summary>
        ///     Создает экземпляр класса используя наименование строки подключения или саму строку
        /// </summary>
        /// <param name="connectionString">Наименование строки подключения или сама строка</param>
        public CloudPublishingContext(string connectionString)
            : base(connectionString)
        {
        }

        /// <summary>
        ///     Возвращает или задает коллекцию типов образований сотрудников, к которой можно делать запросы
        /// </summary>
        public DbSet<Education> Educations { get; set; }

        /// <summary>
        ///     Возвращает или задает коллекцию сотрудников, к которой можно делать запросы
        /// </summary>
        public DbSet<Employee> Employees { get; set; }

        /// <summary>
        ///     Возвращает или задает коллекцию рецензий, к которой можно делать запросы
        /// </summary>
        public DbSet<Review> Review { get; set; }

        /// <summary>
        ///     Возвращает или задает коллекцию изданий, к которой можно делать запросы
        /// </summary>
        public DbSet<Publishing> Publishings { get; set; }

        /// <summary>
        ///     Возвращает или задает коллекцию тематик, к которой можно делать запросы
        /// </summary>
        public DbSet<Topic> Topics { get; set; }

        public DbSet<PublishingEmployee> PublishingEmployees { get; set; }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new EmployeeEntityConfiguration());
            modelBuilder.Configurations.Add(new EducationEntityConfiguration());
            modelBuilder.Configurations.Add(new ReviewEntityConfiguration());
            modelBuilder.Configurations.Add(new PublishingEntityConfiguration());
            modelBuilder.Configurations.Add(new TopicEntityConfiguration());
            modelBuilder.Configurations.Add(new PublishingEmployeeEntityConfiguration());
        }
    }
}