using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CloudPublishing.Data.Entities
{
    [Table("publishing.review")]
    public partial class Review
    {
        [Key]
        [Column("article_id", Order = 0)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Article_id { get; set; }

        [Key]
        [Column("reviwer_id", Order = 1)]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int Reviwer_id { get; set; }

        [Column("content", TypeName = "text")]
        [Required]
        [StringLength(65535)]
        public string Content { get; set; }

        [Column("approved")]
        public bool Approved { get; set; }
    }
}
