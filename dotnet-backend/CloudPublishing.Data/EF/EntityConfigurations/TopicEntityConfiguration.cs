using CloudPublishing.Data.Entities;
using System.Data.Entity.ModelConfiguration;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    class TopicEntityConfiguration : EntityTypeConfiguration<Topic>
    {
        public TopicEntityConfiguration()
        {
            ToTable("topic");
            HasKey(x => x.Id);
            Property(x => x.Id).HasColumnName("id");
            Property(x => x.Name).HasMaxLength(255).HasColumnName("name").IsRequired();
        }
    }
}
