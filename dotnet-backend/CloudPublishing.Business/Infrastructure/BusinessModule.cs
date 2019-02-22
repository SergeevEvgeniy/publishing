using Autofac;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Business.Infrastructure
{
    public class BusinessModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>().WithParameter("connectionString", "EmployeeContext");
            // Для тестирования менть строку, но перед пушем возвращать обратно (временное решение)
        }
    }
}