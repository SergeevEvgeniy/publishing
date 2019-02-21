using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Shared.DTO
{
    public class EducationDTO
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(255)]
        public string Title { get; set; }
    }
}