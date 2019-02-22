using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Identity.Entities;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Employees.Entities
{
    [Table("publishing.employee")]
    public class Employee : BasicEntity, IUser<int>
    {
        [Required]
        [StringLength(255)]
        [Column("first_name")]
        public string FirstName { get; set; }

        [Required]
        [StringLength(255)]
        [Column("last_name")]
        public string LastName { get; set; }

        [StringLength(255)]
        [Column("middle_name")]
        public string MiddleName { get; set; }

        [Required]
        [StringLength(255)]
        [Column("email")]
        public string UserName { get; set; }

        [NotMapped]
        public string Password { get; set; }

        [Required]
        [StringLength(255)]
        [Column("password")]
        public string PasswordHash { get; set; }

        [Column("sex", TypeName = "char")]
        [Required]
        [StringLength(1)]
        public string Sex { get; set; }

        [Column("birth_year")] public short BirthYear { get; set; }

        [Required]
        [StringLength(255)]
        [Column("address")]
        public string Address { get; set; }

        [Column("type", TypeName = "char")]
        [Required]
        [StringLength(1)]
        public string Type { get; set; }

        [Column("education_id")] public int? EducationId { get; set; }

        [Column("chief_editor")] public bool ChiefEditor { get; set; }

        public Education Education { get; set; }

        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<Employee, int> manager, string authenticationType)
        {
            return await manager.CreateIdentityAsync(this, authenticationType);
        }
    }
}