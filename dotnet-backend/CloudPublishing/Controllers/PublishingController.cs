using AutoMapper;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Converters;
using CloudPublishing.Models.Publishings;
using CloudPublishing.Util;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class PublishingController : Controller
    {
        private IPublishingService publishingService;
        private IMapper mapper;
        public PublishingController(IPublishingService publishingService)
        {
            this.publishingService = publishingService;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new PublishingMapProfile())).CreateMapper();
        }

        public ActionResult List()
        {
            var publishings = mapper.Map<IEnumerable<PublishingTableViewModel>>(publishingService.GetAllPublishings());
            return View(publishings);
        }

        public ActionResult CreatePublishing()
        {
            PublishingCreateViewModel publishingViewModel = new PublishingCreateViewModel
            {
                Publishing = new PublishingViewModel(),
                AvailableTopics = publishingService.GetAllTopics().Select(x => x.ToViewModel()),
                AvailableEmployees = publishingService.GetAllEmployees().Select(x => x.ToViewModel())
            };
            return View(publishingViewModel);
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

            PublishingEditViewModel publishingViewModel = new PublishingEditViewModel
            {
                Publishing = publishing.ToViewModel(),
                AvailableTopics = publishingService.GetTopicsNotInPublishing(id)
                    .Select(x => x.ToViewModel()),
                AvailableEmployees = publishingService.GetEmployeesNotInPublishing(id)
                    .Select(x => x.ToViewModel()),
                EmployeesAtPublishing = publishing.Employees.Select(x => x.ToViewModel()),
                TopicsAtPublishing = publishing.Topics.Select(x => x.ToViewModel())
            };

            return View(publishingViewModel);
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