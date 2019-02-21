using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CloudPublishing.Data.Entities
{
    [Table("publishing.education")]
    public class Education
    {
        public Education()
        {
            Employees = new HashSet<Employee>();
        }

        [Key]
        [Column("id")]
        public int Id { get; set; }

        [Required]
        [StringLength(255)]
        [Column("title")]
        public string Title { get; set; }

        public ICollection<Employee> Employees { get; set; }
    }
}