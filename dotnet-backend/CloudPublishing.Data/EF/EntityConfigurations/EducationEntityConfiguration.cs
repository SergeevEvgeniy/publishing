using System.Data.Entity.ModelConfiguration;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    /// <inheritdoc />
    /// <summary>
    ///     Класс конфигурации FluentApi Entity Framework для сущности типа образования
    /// </summary>
    public class EducationEntityConfiguration : EntityTypeConfiguration<Education>
    {
        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр класса, настраивая поля сущности образования а также связи с данной сущностью в базе данных
        /// </summary>
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