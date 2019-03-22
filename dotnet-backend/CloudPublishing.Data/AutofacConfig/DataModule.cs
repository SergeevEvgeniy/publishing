using Autofac;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;
using CloudPublishing.Data.Util;

namespace CloudPublishing.Data.AutofacConfig
{
    public class DataModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<MySql.Data.MySqlClient.MySqlProviderServices>().As<MySql.Data.MySqlClient.MySqlProviderServices>();
            builder.RegisterType<CloudPublishingContext>().AsSelf()
                .WithParameter("connectionString", "CloudPublishingContext");
            
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>();

            builder.RegisterType<PasswordHasher>().As<IPasswordHasher>();
        }
    }
}
