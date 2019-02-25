using System.Data.Entity.ModelConfiguration;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class EducationEntityConfiguration : EntityTypeConfiguration<Education>
    {
        public EducationEntityConfiguration()
        {
            ToTable("publishing.education");
            HasKey(e => e.Id);

            Property(e => e.Id)
                .HasColumnName("id");

            Property(e => e.Title)
                .IsUnicode(false)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("title");

            HasMany(e => e.Employees)
                .WithOptional(e => e.Education)
                .HasForeignKey(e => e.EducationId);
        }
    }
}