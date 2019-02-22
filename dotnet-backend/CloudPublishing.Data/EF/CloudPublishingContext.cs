using System.Data.Entity;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF
{
    public class CloudPublishingContext : DbContext
    {
        public CloudPublishingContext(string connectionString)
            : base(connectionString)
        {
        }

        public virtual DbSet<Education> Educations { get; set; }
        public virtual DbSet<Employee> Employees { get; set; }
        public virtual DbSet<Review> Review { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Education>()
                .Property(e => e.Title)
                .IsUnicode(false);

            modelBuilder.Entity<Education>()
                .HasMany(e => e.Employees)
                .WithOptional(e => e.Education)
                .HasForeignKey(e => e.EducationId);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.FirstName)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.LastName)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.MiddleName)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.Password)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.Sex)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.Address)
                .IsUnicode(false);

            modelBuilder.Entity<Entities.Employee>()
                .Property(e => e.Type)
                .IsUnicode(false);

            modelBuilder.Entity<Review>()
                .Property(e => e.Content)
                .IsUnicode(false);
        }
    }
}