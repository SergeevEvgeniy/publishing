using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Accounts
{
    public class EmployeeUserCreateModel
    {
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
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        [StringLength(255)]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [Required]
        [StringLength(255)]
        [DataType(DataType.Password)]
        [Compare("Password")]
        public string ConfirmPassword { get; set; }

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
    }
}