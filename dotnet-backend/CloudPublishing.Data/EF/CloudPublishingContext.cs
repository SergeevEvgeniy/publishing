using CloudPublishing.Data.EF.EntityConfigurations;
using CloudPublishing.Data.Entities;
using System.Data.Entity;

namespace CloudPublishing.Data.EF
{
    public class CloudPublishingContext : DbContext
    {
        public CloudPublishingContext(string connectionString)
            : base(connectionString)
        {
        }

        public DbSet<Education> Educations { get; set; }
        public DbSet<Employee> Employees { get; set; }
        public DbSet<Review> Review { get; set; }
        public DbSet<Publishing> Publishings { get; set; }
        public DbSet<Topic> Topics { get; set; }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new EmployeeEntityConfiguration());
            modelBuilder.Configurations.Add(new EducationEntityConfiguration());
            modelBuilder.Configurations.Add(new ReviewEntityConfiguration());
            modelBuilder.Configurations.Add(new PublishingEntityConfiguration());
            modelBuilder.Configurations.Add(new TopicEntityConfiguration());
        }
    }
}