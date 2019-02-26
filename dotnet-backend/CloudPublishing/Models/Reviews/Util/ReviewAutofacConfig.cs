using Autofac;
using Autofac.Integration.Mvc;
using CloudPublishing.Models.Reviews.Repositories;
using System.Web.Mvc;

namespace CloudPublishing.Models.Reviews.Util
{
    public class ReviewAutofacConfig
    {
        public static void ConfigureContainer()
        {
            var builder = new ContainerBuilder();
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterType<ReviewRepository>().As<IReviewRepository>();
            var container = builder.Build();
            DependencyResolver.SetResolver(new AutofacDependencyResolver(container));
        }
    }
}