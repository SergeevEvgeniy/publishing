using System;
using System.Collections.Generic;
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