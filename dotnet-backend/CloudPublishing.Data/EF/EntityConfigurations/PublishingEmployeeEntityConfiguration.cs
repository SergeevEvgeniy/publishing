using CloudPublishing.Data.Entities;
using System.Data.Entity.ModelConfiguration;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class PublishingEmployeeEntityConfiguration : EntityTypeConfiguration<PublishingEmployee>
    {
        public PublishingEmployeeEntityConfiguration()
        {
            ToTable("publishing_employee");
            HasKey(pe => new { pe.EmployeeId, pe.PublishingId });
            Property(pe => pe.PublishingId).HasColumnName("publishing_id");
            Property(pe => pe.EmployeeId).HasColumnName("employee_id");
        }
    }
}
