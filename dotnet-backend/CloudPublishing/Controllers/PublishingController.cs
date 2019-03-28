using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Publishings.ViewModels;
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
                .Map<IEnumerable<PublishingTableViewModel>>(publishingService.GetPublishings());
            return View(publishings);
        }

        public ActionResult Create()
        {

            PublishingCreateViewModel publishingViewModel = new PublishingCreateViewModel
            {
                Publishing = new PublishingViewModel(),

                AvailableTopics = mapper
                    .Map<IEnumerable<TopicViewModel>>(publishingService.GetTopics()),

                AvailableJournalists = mapper
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetJournalists()),

                AvailableEditors = mapper
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishingService.GetEditors())
            };
            return View(publishingViewModel);
        }

        [HttpPost]
        public ActionResult Create(PublishingViewModel publishing)
        {
            // TODO: Валидация нового журнала
            var publishingDTO = mapper.Map<PublishingDTO>(publishing);
            publishingService.CreatePublishing(publishingDTO);
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
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishing.Editors),

                JournalistsAtPublishing = mapper
                    .Map<IEnumerable<PublishingEmployeeViewModel>>(publishing.Journalists),

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