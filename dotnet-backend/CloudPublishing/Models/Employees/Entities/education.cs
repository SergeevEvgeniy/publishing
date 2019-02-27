using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CloudPublishing.Models.Employees.Entities
{
    [Table("publishing.education")]
    public class Education : BasicEntity
    {
        public Education()
        {
            Employees = new HashSet<Employee>();
        }

        [Required]
        [StringLength(255)]
        [Column("title")]
        public string Title { get; set; }

        public ICollection<Employee> Employees { get; set; }
    }
}