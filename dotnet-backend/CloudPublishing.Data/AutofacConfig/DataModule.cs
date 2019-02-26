using Autofac;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Data.AutofacConfig
{
    public class DataModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<MySql.Data.MySqlClient.MySqlProviderServices>().As<MySql.Data.MySqlClient.MySqlProviderServices>();

            // Для тестирования менть строку, но перед пушем возвращать обратно (временное решение)
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>().WithParameter("connectionString", "PublishingContext");
        }
    }
}
