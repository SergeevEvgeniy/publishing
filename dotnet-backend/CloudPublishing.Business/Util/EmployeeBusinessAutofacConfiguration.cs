using Autofac;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Business.Util
{
    public class EmployeeBusinessAutofacConfiguration
    {
        public IContainer ConfigureContainer()
        {
            var builder = new ContainerBuilder();
            builder.RegisterType<EmployeeRepository>().As<IEmployeeRepository>();
            builder.RegisterType<()

            return builder.Build();
        }
    }
}