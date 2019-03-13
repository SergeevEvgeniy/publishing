using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;
using Autofac;
using Autofac.Integration.Mvc;
using Autofac.Integration.WebApi;
using AutofacConfig;
using CloudPublishing.AutofacConfig;
using CloudPublishing.Business.Services;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing
{
    public class MvcApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            //ConfigureContainer();

            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);

            GlobalConfiguration.Configuration.Formatters
                .JsonFormatter.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
            GlobalConfiguration.Configuration.Formatters
                .JsonFormatter.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
        }

        //private void ConfigureContainer()
        //{
        //    var builder = new ContainerBuilder();
        //    builder.RegisterModule(new GlobalModule());
        //    builder.RegisterModule(new WebModule());

        //    var container = builder.Build();
        //    DependencyResolver.SetResolver(new AutofacDependencyResolver(container));
        //    GlobalConfiguration.Configuration.DependencyResolver = new AutofacWebApiDependencyResolver(container);
        //}
    }
}