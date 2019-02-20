using System.Security.Claims;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.DTO;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Accounts.Identity.Entities
{
    public class EmployeeUser : IUser<int>
    {
        public int Id { get; set; }

        public string UserName { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string MiddleName { get; set; }

        public string Password { get; set; }

        public string PasswordHash { get; set; }

        public string Sex { get; set; }

        public short BirthYear { get; set; }

        public string Address { get; set; }

        public string Type { get; set; }

        public int? EducationId { get; set; }

        public EducationDTO Education { get; set; }

        public bool ChiefEditor { get; set; }

        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<EmployeeUser, int> manager, string authenticationType)
        {
            var userIdentity = await manager.CreateIdentityAsync(this, authenticationType);
            // Add custom user claims here
            return userIdentity;
        }
    }
}