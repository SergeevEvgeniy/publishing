using System;
using System.Collections.Generic;
using System.Reflection;
using Autofac;
using Autofac.Integration.Mvc;
using Autofac.Integration.WebApi;
using AutoMapper;
using CloudPublishing.Business.Util;
using CloudPublishing.Util.Profiles;
using Module = Autofac.Module;

namespace CloudPublishing.AutofacConfig
{
    public class WebModule : Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterApiControllers(Assembly.GetExecutingAssembly());
            builder.Register(c => new MapperConfiguration(cfg => cfg.AddProfiles(new List<Type>
            {
                typeof(PublishingMapProfile),
                typeof(EmployeeMapProfile),
                typeof(ReviewMapProfile),
                typeof(EmployeeBusinessMapProfile),
                typeof(PublishingBusinessMapProfile),
                typeof(ReviewBusinessMapProfile)
            })).CreateMapper()).As<IMapper>();
        }
    }
}