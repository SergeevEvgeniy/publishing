using Autofac;
using Autofac.Integration.Mvc;
using Autofac.Integration.WebApi;
using System.Reflection;
using System.Web;

namespace CloudPublishing.AutofacConfig
{
    public class WebModule : Autofac.Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterApiControllers(Assembly.GetExecutingAssembly());

            builder.Register(c => HttpContext.Current.GetOwinContext().Authentication).InstancePerRequest();
        }
    }
}