using CloudPublishing;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;
using Microsoft.AspNet.Identity;
using Microsoft.Owin;
using Microsoft.Owin.Security.Cookies;
using Owin;

[assembly: OwinStartup(typeof(Startup))]

namespace CloudPublishing
{
    public class Startup
    {
        private readonly IEmployeeServiceCreator creator;
        public Startup()
        {
            creator = new EmployeeServiceCreator();
        }

        public void Configuration(IAppBuilder app)
        {
            app.CreatePerOwinContext<IEmployeeService>(() => creator.Create());
            app.UseCookieAuthentication(new CookieAuthenticationOptions
            {
                AuthenticationType = DefaultAuthenticationTypes.ApplicationCookie,
                LoginPath = new PathString("/Employee/Login"),
            });
        }
    }
}