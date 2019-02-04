using System.Web;
using System.Web.Optimization;

namespace CloudPublishing
{
    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/bundles/libs").Include(
                      "~/Scripts/libs/bootstrap.js",
                        "~/Scripts/libs/jquery.js"));

            //Новые скрипты добавлять в этот бандл, а лучше новый бандл для каждой страницы
            bundles.Add(new ScriptBundle("~/bundles/main").Include(
          "~/Scripts/main.js"));

            bundles.Add(new StyleBundle("~/Content/css").Include(
                      "~/Content/bootstrap.css",
                      "~/Content/bootstrap-theme.css",
                      "~/Content/site.css"));
        }
    }
}
