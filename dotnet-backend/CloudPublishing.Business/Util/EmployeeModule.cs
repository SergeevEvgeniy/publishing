using Autofac;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Business.Util
{
    public class EmployeeModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<UnitOfWork>().As<IUnitOfWork>().WithParameter("connectionString", "EmployeeContext");// TODO: Общий вообще то
            builder.RegisterType<EmployeeService>().As<IEmployeeService>();
        }
    }
}