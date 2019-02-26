using Autofac;
using CloudPublishing.Business.AutofacConfig;
using CloudPublishing.Data.AutofacConfig;

namespace AutofacConfig
{
    public class GlobalModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterModule(new BusinessModule());
            builder.RegisterModule(new DataModule());
        }
    }
}
