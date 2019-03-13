using System.Web.Optimization;

namespace CloudPublishing
{
    public class BundleConfig
    {
        // For more information on bundling, visit http://go.microsoft.com/fwlink/?LinkId=301862
        public static void RegisterBundles(BundleCollection bundles)
        {
            bundles.Add(new ScriptBundle("~/bundles/libs").Include(
                "~/Scripts/jquery-3.0.0.js",
                "~/Scripts/jquery.validate.js",
                "~/Scripts/jquery.validate.unobtrusive.js",
                "~/Scripts/bootstrap.js"));

            //Новые скрипты добавлять в этот бандл, а лучше новый бандл для каждой страницы
            bundles.Add(new ScriptBundle("~/bundles/main").Include(
                "~/Scripts/main.js"));

            bundles.Add(new ScriptBundle("~/bundles/employeeScripts").Include(
                "~/Scripts/custom/employeeComponents.js"));

            bundles.Add(new StyleBundle("~/Content/css").Include(
                "~/Content/bootstrap.css",
                "~/Content/bootstrap-theme.css",
                "~/Content/site.css",
                "~/Content/custom/publishing-create-form-styles.css"));

            bundles.Add(new ScriptBundle("~/bundles/publishing-create-view").Include(
                "~/Scripts/custom/publishingMultiSelectComponent.js"));
        }
    }
}