using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace CloudPublishing
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
            routes.MapRoute(
                name: "Authors list",
                url: "{controller}/{action}/{publishingId}/{topicId}",
                defaults: new { controller = "Review", action = "GetAuthorList",
                    publishingId = UrlParameter.Optional,
                    topicId = UrlParameter.Optional }
            );
            routes.MapRoute(
                name: "Articles list",
                url: "{controller}/{action}/{publishingId}/{topicId}/{authorId}",
                defaults: new { controller = "Review", action = "GetArticleList",
                    publishingId = UrlParameter.Optional,
                    topicId = UrlParameter.Optional,
                    authorId = UrlParameter.Optional }
            );
        }
    }
}
