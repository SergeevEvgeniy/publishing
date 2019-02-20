using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Identity.Entities;
using CloudPublishing.Models.Employees.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Identity.Managers
{
    public class EmployeeUserManager : UserManager<EmployeeUser, int>
    {
        public EmployeeUserManager(IUserStore<EmployeeUser, int> store) : base(store)
        {
        }

        public static EmployeeUserManager Create(IdentityFactoryOptions<EmployeeUserManager> options, IOwinContext context)
        {
            var dbContext = context.Get<EmployeeContext>();
            var manager = new EmployeeUserManager(new EmployeeStore(dbContext))
            {
                PasswordValidator = new PasswordValidator()
                {
                    RequiredLength = 5,
                    RequireDigit = true,
                    RequireLowercase = true,
                    RequireNonLetterOrDigit = true,
                    RequireUppercase = true
                }
            };

            return manager;
        }
    }
}