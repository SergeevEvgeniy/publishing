using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(CloudPublishing.Startup))]

namespace CloudPublishing
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {

        }

    }
}