using CloudPublishing.Models.Publishings.Entities;
using System.Data.Entity;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingContext : DbContext
    {
        public PublishingContext()
            : base("PublishingContext")
        { }

        public DbSet<Publishing> Publishings { get; set; }
        public DbSet<Topic> Topics { get; set; }
    }
}