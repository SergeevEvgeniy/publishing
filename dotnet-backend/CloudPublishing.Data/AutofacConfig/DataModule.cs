using Autofac;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Managers;
using CloudPublishing.Data.Identity.Stores;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.AutofacConfig
{
    public class DataModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<MySql.Data.MySqlClient.MySqlProviderServices>().As<MySql.Data.MySqlClient.MySqlProviderServices>();
            builder.RegisterType<CloudPublishingContext>().AsSelf().WithParameter("connectionString", "EmployeeContext")
                .InstancePerRequest();
            
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>();

            builder.RegisterType<EmployeeUserStore>().AsImplementedInterfaces().InstancePerRequest();
            builder.Register(c => new EmployeeUserManager(c.Resolve<IUserRoleStore<EmployeeUser, int>>()))
                .As<UserManager<EmployeeUser, int>>().InstancePerRequest();
        }
    }
}
