using CloudPublishing.Data.EF;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Stores;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin;

namespace CloudPublishing.Data.Identity.Managers
{
    public class EmployeeUserManager : UserManager<EmployeeUser, int>
    {
        public EmployeeUserManager(IUserStore<EmployeeUser, int> store) : base(store)
        {
        }

        public static EmployeeUserManager Create(IdentityFactoryOptions<EmployeeUserManager> options, IOwinContext context)
        {
            var appDbContext = context.Get<CloudPublishingContext>();
            var appUserManager = new EmployeeUserManager(new EmployeeUserStore(appDbContext));

            return appUserManager;
        }
    }
}