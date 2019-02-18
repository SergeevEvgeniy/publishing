using System.Web.Mvc;
using Autofac;
using Autofac.Integration.Mvc;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories;
using CloudPublishing.Models.Employees.Repositories.Interfaces;
using CloudPublishing.Models.Employees.Services;
using CloudPublishing.Models.Employees.Services.Interfaces;

namespace CloudPublishing.Models.Employees.Util
{
    public class EmployeeAutofacConfiguration
    {
        public static void ConfigureContainer()
        {
            var builder = new ContainerBuilder();
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterType<EmployeeRepository>().As<IEmployeeRepository>();

            builder.RegisterType<EmployeeService>().As<IEmployeeService>();

            var container = builder.Build();
            DependencyResolver.SetResolver(new AutofacDependencyResolver(container));
        }
    }
}