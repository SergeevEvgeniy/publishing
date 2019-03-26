using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Publishings.ViewModels;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using CloudPublishing.Business.Enums;

namespace CloudPublishing.Controllers
{
    public class PublishingController : Controller
    {
        private IPublishingService publishingService;
        private IEmployeeService employeeService;
        private IMapper mapper;

        public PublishingController(IPublishingService publishingService, IEmployeeService employeeService, IMapper mapper)
        {
            this.publishingService = publishingService;
            this.employeeService = employeeService;
            this.mapper = mapper;
        }

        public ActionResult List()
        {
            IEnumerable<PublishingTableViewModel> publishings = mapper
                .Map<IEnumerable<PublishingTableViewModel>>(publishingService.GetAllPublishings());
            return View(publishings);
        }

        public ActionResult Create()
        {

            PublishingCreateViewModel publishingViewModel = new PublishingCreateViewModel
            {
                Publishing = new PublishingViewModel(),

                AvailableTopics = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishingService.GetAllTopics()),

                AvailableJournalists = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetJournalistList()),

                AvailableEditors = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetEditorList())
            };
            return View(publishingViewModel);
        }

        [HttpPost]
        public ActionResult Create(PublishingViewModel publishing)
        {
            // TODO: Валидация нового журнала
            publishingService.CreatePublishing(mapper.Map<PublishingDTO>(publishing));
            return RedirectToAction("List");
        }

        public ActionResult Edit(int id)
        {
            PublishingDTO publishing = publishingService.GetPublishing(id);
            if (publishing == null)
            {
                return null;
            }

            PublishingEditViewModel editViewModel = new PublishingEditViewModel
            {
                Publishing = mapper.Map<PublishingViewModel>(publishing),

                AvailableTopics = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishingService.GetTopicsNotInPublishing(id)),

                TopicsAtPublishing = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishing.Topics),

                EditorsAtPublishing = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishing.Employees.Where(e => e.Type == "E")),

                JournalistsAtPublishing = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishing.Employees.Where(e => e.Type == "J")),

                AvailableEditors = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetEditorsNotInPublishing(id)),

                AvailableJournalists = mapper
                .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetJournalistsNotInPublishing(id)),
            };

            return View(editViewModel);
        }

        [HttpPost]
        public ActionResult Edit(PublishingViewModel publishing)
        {
            // TODO: Валидация редактированного журнала
            PublishingDTO publishingDTO = mapper.Map<PublishingDTO>(publishing);
            publishingService.UpdatePublishing(publishingDTO);
            return RedirectToAction("List");
        }

        public ActionResult Delete(int id)
        {
            publishingService.DeletePublishing(id);
            return RedirectToAction("List");
        }
    }
}