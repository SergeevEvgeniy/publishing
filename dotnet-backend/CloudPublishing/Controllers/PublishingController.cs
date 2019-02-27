using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Publishings;
using System.Linq;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class PublishingController : Controller
    {
        private IPublishingService publishingService;

        public PublishingController(IPublishingService publishingService)
        {
            this.publishingService = publishingService;
        }

        public ActionResult List()
        {
            var publishings = publishingService.GetAllPublishings().Select(x => new PublishingTableViewModel(x));
            return View(publishings);
        }
    }
}