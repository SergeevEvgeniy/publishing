using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Converters;
using CloudPublishing.Models.Publishings;
using CloudPublishing.Models.Publishings.Enums;
using System.Collections.Generic;
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

        public ActionResult CreatePublishing()
        {
            PublishingCreateViewModel publishingViewModel = new PublishingCreateViewModel
            {
                Publishing = new PublishingViewModel(),
                TopicsNotAtPublishing = publishingService.GetAllTopics().Select(x => x.ToViewModel()),
                EmployeesNotInPublishing = publishingService.GetAllEmployees().Select(x => x.ToViewModel()),
                EmployeesAtPublishing = null,
                TopicsAtPublishing = null
            };

            ViewBag.Action = "CreatePublishing";
            return View("PublishingInDetail", publishingViewModel);
        }

        [HttpPost]
        public ActionResult CreatePublishing(PublishingViewModel publishing)
        {
            //TODO: Валидация нового журнала
            publishingService.CreatePublishing(publishing.ToDTO());
            return RedirectToAction("List");
        }

        public ActionResult EditPublishing(int id)
        {
            var publishing = publishingService.GetPublishing(id);
            if (publishing == null)
            {
                return null;
            }

            PublishingCreateViewModel publishingVM = new PublishingCreateViewModel
            {
                Publishing = publishing.ToViewModel(),
                EmployeesNotInPublishing = publishingService.GetNotInPublishingEmployees(id).Select(x => x.ToViewModel()),
                TopicsNotAtPublishing = publishingService.GetNotAtPublishingTopics(id).Select(x => x.ToViewModel()),
                EmployeesAtPublishing = publishing.Employees.Select(x => x.ToViewModel()),
                TopicsAtPublishing = publishing.Topics.Select(x => x.ToViewModel())

            };
            ViewBag.Action = "EditPublishing";
            return View("PublishingInDetail", publishingVM);
        }

        [HttpPost]
        public ActionResult EditPublishing(PublishingViewModel publishing)
        {
            // TODO: Валидация редактированного журнала
            publishingService.UpdatePublishing(publishing.ToDTO());
            return RedirectToAction("List");
        }

        public ActionResult DeletePublishing(int id)
        {
            publishingService.DeletePublishing(id);
            return RedirectToAction("List");
        }
    }
}