using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Business.Services
{
    public class PublishingService : IPublishingService
    {
        private readonly IUnitOfWork unitOfWork;
        private readonly IMapper mapper;
        private readonly IEmployeeService employeeService;

        public PublishingService(IUnitOfWork unitOfWork, IMapper mapper, IEmployeeService employeeService)
        {
            this.unitOfWork = unitOfWork;
            this.mapper = mapper;
            this.employeeService = employeeService;
        }

        public IEnumerable<PublishingDTO> GetAllPublishings()
        {
            var publishings = unitOfWork.Publishings.GetAll();
            var publishingsDTO = mapper.Map<IEnumerable<PublishingDTO>>(publishings);
            return mapper.Map<IEnumerable<PublishingDTO>>(publishings);
        }

        public PublishingDTO GetPublishing(int id)
        {
            var publishing = unitOfWork.Publishings.Get(id);
            var publishingDTO = mapper.Map<PublishingDTO>(publishing);
            publishingDTO.Editors = employeeService
                .GetEmployeeList("E", publishing.PublishingEmployees.Select(p => p.EmployeeId));
            publishingDTO.Journalists = employeeService
                .GetEmployeeList("J", publishing.PublishingEmployees.Select(p => p.EmployeeId));
            return publishingDTO;
        }

        public void CreatePublishing(PublishingDTO publishing)
        {
            var publishingEntity = mapper.Map<Publishing>(publishing);
            var employees = publishing.Journalists.Concat(publishing.Editors);
            publishingEntity.PublishingEmployees = employees
                .Select(e => new PublishingEmployee { EmployeeId = e.Id, PublishingId = publishing.Id }).ToList();
            unitOfWork.Publishings.Create(publishingEntity);
            unitOfWork.Save();
        }

        public void UpdatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<Publishing>(publishing);
            var employees = publishing.Journalists.Concat(publishing.Editors);
            publishingEntity.PublishingEmployees = employees
                .Select(e => new PublishingEmployee { EmployeeId = e.Id, PublishingId = publishing.Id }).ToList();
            unitOfWork.Publishings.Update(publishingEntity);
            unitOfWork.Save();
        }

        public void DeletePublishing(int id)
        {
            unitOfWork.Publishings.Delete(id);
            unitOfWork.Save();
        }

        public IEnumerable<TopicDTO> GetAllTopics()
        {
            var topics = unitOfWork.Topics.GetAll();
            return mapper.Map<IEnumerable<TopicDTO>>(topics);
        }

        public IEnumerable<EmployeeDTO> GetJournalistList()
        {
            return employeeService.GetEmployeeList("J");
        }

        public IEnumerable<EmployeeDTO> GetEditorList()
        {
            return employeeService.GetEmployeeList("E");
        }

        public IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId)
        {
            var topics = unitOfWork.Topics.GetAll()
                .Where(t => !t.Publishings.Select(p => p.Id).Contains(publishingId));

            return mapper.Map<IEnumerable<TopicDTO>>(topics);
        }

        public IEnumerable<EmployeeDTO> GetEditorsNotInPublishing(int publishingId)
        {
            var publishingEmployeesIds = unitOfWork.Publishings.Get(publishingId).PublishingEmployees.Select(e => e.EmployeeId);
            var editors = employeeService.GetEmployeeList("E");
            return editors.Where(e => !publishingEmployeesIds.Contains(e.Id));
        }

        public IEnumerable<EmployeeDTO> GetJournalistsNotInPublishing(int publishingId)
        {
            var publishingEmployeesIds = unitOfWork.Publishings.Get(publishingId).PublishingEmployees.Select(e => e.EmployeeId);
            var journalists = employeeService.GetEmployeeList("J");
            return journalists.Where(e => !publishingEmployeesIds.Contains(e.Id));
        }
    }
}
