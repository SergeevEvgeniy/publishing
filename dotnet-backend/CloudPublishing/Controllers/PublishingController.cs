using CloudPublishing.Models.Publishings.Entities;
using CloudPublishing.Models.Publishings.Repositories;
using System.Collections.Generic;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class PublishingController : Controller
    {
        IPublishingRepository repository;

        public PublishingController()
        {
            //TODO: Внедрить зависимости
            repository = new PublishingRepository();
        }
        // GET: Publishing
        public ActionResult List()
        {
            return View(repository.Publishings);
        }
    }
}