using CloudPublishing.Models.Employees.EF;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Identity
{
    public class EmployeeManager : UserManager<EmployeeIdentity, int>
    {
        public EmployeeManager(IUserStore<EmployeeIdentity, int> store) : base(store)
        {
        }

        public static EmployeeManager Create(IdentityFactoryOptions<EmployeeManager> options, IOwinContext context)
        {
            return new EmployeeManager(new EmployeeStore(context.Get<EmployeeContext>()));
        }
    }
}