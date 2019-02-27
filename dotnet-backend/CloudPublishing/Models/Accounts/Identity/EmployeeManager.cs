using CloudPublishing.Models.Employees.EF;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Accounts.Identity
{
    public class EmployeeManager : UserManager<EmployeeUser, int>
    {
        public EmployeeManager(IUserStore<EmployeeUser, int> store) : base(store)
        {
        }

        public static EmployeeManager Create(IdentityFactoryOptions<EmployeeManager> options, IOwinContext context)
        {
            return new EmployeeManager(new EmployeeStore(context.Get<EmployeeContext>()));
        }
    }
}