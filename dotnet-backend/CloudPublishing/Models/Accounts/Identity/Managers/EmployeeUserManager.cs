using CloudPublishing.Models.Accounts.Identity.Entities;
using CloudPublishing.Models.Accounts.Identity.Stores;
using CloudPublishing.Models.Employees.EF;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Accounts.Identity.Managers
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