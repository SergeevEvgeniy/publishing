using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CloudPublishing.Data.Entities
{
    [Table("publishing.review")]
    public class Review
    {
        [Key]
        [Column("article_id", Order = 0)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int ArticleId { get; set; }

        [Key]
        [Column("reviwer_id", Order = 1)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int ReviwerId { get; set; }

        [Column("content", TypeName = "text")]
        [Required]
        [StringLength(65535)]
        public string Content { get; set; }

        [Column("approved")]
        public bool Approved { get; set; }
    }
}
