using Autofac;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.Business.AutofacConfig
{
    public class BusinessModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<EmployeeService>().As<IEmployeeService>();
            builder.RegisterType<ReviewService>().As<IReviewService>();
            builder.RegisterType<PublishingService>().As<IPublishingService>();
            builder.RegisterType<ArticleService>().As<IArticleService>();
            builder.RegisterType<FakeArticleService>().AsImplementedInterfaces();
        }
    }
}
