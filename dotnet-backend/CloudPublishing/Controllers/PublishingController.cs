using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Publishings;
using System.Collections.Generic;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class PublishingController : Controller
    {
        private IPublishingService publishingService;
        private IMapper mapper;

        public PublishingController(IPublishingService publishingService, IMapper mapper)
        {
            this.publishingService = publishingService;
            this.mapper = mapper;
        }

        public ActionResult List()
        {
            IEnumerable<PublishingTableViewModel> publishings = mapper
                .Map<IEnumerable<PublishingTableViewModel>>(publishingService.GetAllPublishings());

            return View(publishings);
        }

        public ActionResult CreatePublishing()
        {
            PublishingCreateViewModel publishingViewModel = new PublishingCreateViewModel
            {
                Publishing = new PublishingViewModel(),

                AvailableTopics = mapper
                .Map<IEnumerable<TopicViewModel>>(publishingService.GetAllTopics()),

                AvailableEmployees = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetAllEmployees())
            };
            return View(publishingViewModel);
        }

        [HttpPost]
        public ActionResult CreatePublishing(PublishingViewModel publishing)
        {
            publishingService.CreatePublishing(mapper.Map<PublishingDTO>(publishing));
            return RedirectToAction("List");
        }

        public ActionResult EditPublishing(int id)
        {
            var publishing = publishingService.GetPublishing(id);
            if (publishing == null)
            {
                return null;
            }

            PublishingEditViewModel editViewModel = new PublishingEditViewModel
            {
                Publishing = mapper.Map<PublishingViewModel>(publishing),

                AvailableTopics = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishingService.GetTopicsNotInPublishing(id)),

                AvailableEmployees = mapper
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetEmployeesNotInPublishing(id)),

                EmployeesAtPublishing = mapper
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishing.Employees),

                TopicsAtPublishing = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishing.Topics)
            };

            return View(editViewModel);
        }

        [HttpPost]
        public ActionResult EditPublishing(PublishingViewModel publishing)
        {
            // TODO: Валидация редактированного журнала
            PublishingDTO publishingDTO = mapper.Map<PublishingDTO>(publishing);
            publishingService.UpdatePublishing(publishingDTO);
            return RedirectToAction("List");
        }

        public ActionResult DeletePublishing(int id)
        {
            publishingService.DeletePublishing(id);
            return RedirectToAction("List");
        }
    }
}