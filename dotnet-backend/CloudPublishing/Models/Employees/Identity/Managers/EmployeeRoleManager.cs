using CloudPublishing.Models.Employees.Identity.Entities;
using CloudPublishing.Models.Employees.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Models.Employees.Identity.Managers
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