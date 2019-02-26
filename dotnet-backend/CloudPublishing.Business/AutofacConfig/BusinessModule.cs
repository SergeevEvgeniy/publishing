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
            builder.RegisterType<EmployeeApiService>().As<IEmployeeApiService>();
            builder.RegisterType<ReviewService>().As<IReviewService>();
        }
    }
}
