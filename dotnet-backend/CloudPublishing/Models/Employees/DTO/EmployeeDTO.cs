using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Employees.DTO
{
    public class EmployeeDTO
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(255)]
        public string FirstName { get; set; }

        [Required]
        [StringLength(255)]
        public string LastName { get; set; }

        [StringLength(255)]
        public string MiddleName { get; set; }

        [Required]
        [StringLength(255)]
        public string Email { get; set; }

        [Required]
        [StringLength(255)]
        public string Password { get; set; }

        [Required]
        [StringLength(1)]
        public string Sex { get; set; }

        public short BirthYear { get; set; }

        [Required]
        [StringLength(255)]
        public string Address { get; set; }

        [Required]
        [StringLength(1)]
        public string Type { get; set; }

        public int? EducationId { get; set; }

        public bool ChiefEditor { get; set; }

        public EducationDTO Education { get; set; }
    }
}