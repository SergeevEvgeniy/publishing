using System.Data.Entity.ModelConfiguration;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class ReviewEntityConfiguration : EntityTypeConfiguration<Review>
    {
        public ReviewEntityConfiguration()
        {
            Property(e => e.Content)
                .IsUnicode(false);
        }
    }
}