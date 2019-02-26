using CloudPublishing;
using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(Startup))]

namespace CloudPublishing
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
        }
    }
}