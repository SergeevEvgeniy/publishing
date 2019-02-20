using CloudPublishing.Models.Accounts.Identity.Entities;
using CloudPublishing.Models.Accounts.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Accounts.Identity.Managers
{
    public class EmployeeRoleManager : RoleManager<EmployeeRole, int>
    {
        public EmployeeRoleManager(IRoleStore<EmployeeRole, int> store) : base(store)
        {
        }

        public static EmployeeRoleManager Create(IdentityFactoryOptions<EmployeeRoleManager> options, IOwinContext context)
        {
            return new EmployeeRoleManager(new EmployeeRoleStore());
        }
    }
}