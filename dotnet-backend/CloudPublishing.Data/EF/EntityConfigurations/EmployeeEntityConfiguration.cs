using System.Data.Entity.ModelConfiguration;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class EmployeeEntityConfiguration : EntityTypeConfiguration<Employee>
    {
        public EmployeeEntityConfiguration()
        {
            ToTable("publishing.employee");
            HasKey(e => e.Id);

            Property(e => e.Id)
                .HasColumnName("id");

            Property(e => e.FirstName)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("first_name")
                .IsUnicode(false);

            Property(e => e.LastName)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("last_name")
                .IsUnicode(false);

            Property(e => e.MiddleName)
                .HasMaxLength(255)
                .HasColumnName("middle_name")
                .IsUnicode(false);

            Property(e => e.Email)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("email")
                .IsUnicode(false);

            Property(e => e.Password)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("password")
                .IsUnicode(false);

            Property(e => e.Sex)
                .IsRequired()
                .HasColumnName("sex")
                .HasColumnType("char")
                .HasMaxLength(1)
                .IsUnicode(false);

            Property(e => e.BirthYear)
                .HasColumnName("birth_year");

            Property(e => e.Address)
                .IsRequired()
                .HasMaxLength(255)
                .HasColumnName("address")
                .IsUnicode(false);

            Property(e => e.Type)
                .IsRequired()
                .HasColumnName("type")
                .HasColumnType("char")
                .HasMaxLength(1)
                .IsUnicode(false);

            Property(e => e.EducationId).HasColumnName("education_id");

            Property(e => e.ChiefEditor).HasColumnName("chief_editor");
        }
    }
}