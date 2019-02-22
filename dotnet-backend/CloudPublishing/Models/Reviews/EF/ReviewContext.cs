namespace CloudPublishing.Models.Reviews
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class ReviewContext : DbContext
    {
        public ReviewContext()
            : base("name=publishingEntities")
        {
        }

        public virtual DbSet<Review> Review { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Review>()
                .Property(e => e.Content)
                .IsUnicode(false);
        }
    }
}
