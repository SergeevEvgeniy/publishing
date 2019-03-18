using Autofac;
using Autofac.Integration.Mvc;
using Autofac.Integration.WebApi;
using AutoMapper;
using AutoMapper.Configuration;
using CloudPublishing.Business.Util;
using CloudPublishing.Util;
using System.Reflection;

namespace CloudPublishing.AutofacConfig
{
    public class WebModule : Autofac.Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterApiControllers(Assembly.GetExecutingAssembly());
            builder.RegisterInstance(GetMapper()).As<IMapper>();
        }

        private IMapper GetMapper()
        {
            MapperConfigurationExpression mce = new MapperConfigurationExpression();
            mce.AddProfile(new PublishingMapProfile());
            mce.AddProfile(new EmployeeMapProfile());
            mce.AddProfile(new ReviewMapProfile());
            mce.AddProfile(new EmployeeBusinessMapProfile());
            mce.AddProfile(new PublishingBusinessMapProfile());
            mce.AddProfile(new ReviewBusinessMapProfile());
            MapperConfiguration mapperConfiguration = new MapperConfiguration(mce);
            return mapperConfiguration.CreateMapper();
        }
    }
}