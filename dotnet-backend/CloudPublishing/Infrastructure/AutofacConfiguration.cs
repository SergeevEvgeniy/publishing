﻿using System.Web.Mvc;
using Autofac;
using Autofac.Integration.Mvc;
using CloudPublishing.Business.Util;

namespace CloudPublishing.Infrastructure
{
    public class AutofacConfiguration
    {
        public static void ConfigureContainer()
        {
            var builder = new ContainerBuilder();
            builder.RegisterControllers(typeof(MvcApplication).Assembly);
            builder.RegisterModule<EmployeeModule>();

            var container = builder.Build();
            DependencyResolver.SetResolver(new AutofacDependencyResolver(container));
        }
    }
}