using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class ErrorController : Controller
    {
        public ActionResult NotFound()
        {
            Response.StatusCode = 404;

            return View(404);
        }
    }
}