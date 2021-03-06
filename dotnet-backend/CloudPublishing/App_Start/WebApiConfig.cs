﻿using System.Web.Http;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            config.Formatters.JsonFormatter.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();

            // Web API routes
            config.MapHttpAttributeRoutes();
            config.Routes.MapHttpRoute(
                name: "PublishingsByType",
                routeTemplate: "api/publishings/{publishingType}",
                defaults: new { controller = "publishings", publishingType = RouteParameter.Optional}
            );
            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );


        }
    }
}
