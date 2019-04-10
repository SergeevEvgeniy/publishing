using CloudPublishing.Data.Entities;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class ReviewEntityConfiguration : EntityTypeConfiguration<Review>
    {
        public ReviewEntityConfiguration()
        {
            ToTable("publishing.review");
            HasKey(r => new { r.ArticleId, r.ReviwerId });

            Property(r => r.ArticleId)
                .HasColumnName("article_id")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None);

            Property(r => r.ReviwerId)
                .HasColumnName("reviwer_id")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.None); ;

            Property(r => r.Content)
                .IsRequired()
                .HasMaxLength(65535)
                .HasColumnType("text")
                .HasColumnName("content");

            Property(r => r.Approved)
                .HasColumnName("approved");

            Ignore(c => c.Employee);
        }
    }
}