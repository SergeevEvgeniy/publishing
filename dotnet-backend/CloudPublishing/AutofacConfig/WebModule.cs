using Autofac;
using Autofac.Integration.Mvc;
using Autofac.Integration.WebApi;
using System.Reflection;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.AutofacConfig
{
    public class WebModule : Autofac.Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterApiControllers(Assembly.GetExecutingAssembly());
            builder.RegisterType<EmployeeServiceCreator>().As<IEmployeeServiceCreator>();
        }
    }
}