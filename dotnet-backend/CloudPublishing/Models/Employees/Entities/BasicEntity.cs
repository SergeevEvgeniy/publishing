using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CloudPublishing.Models.Employees.Entities
{
    public class BasicEntity
    {
        [Key]
        [Column("id")]
        public int Id { get; set; }
    }
}