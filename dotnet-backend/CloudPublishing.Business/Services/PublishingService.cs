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
            publishingDTO.Employees = employeeService
                .GetEmployeeList("E", publishing.PublishingEmployees.Select(p => p.Employeee))
                .Union(employeeService
                .GetEmployeeList("J", publishing.PublishingEmployees.Select(p => p.Employeee)));
            return publishingDTO;
        }

        public void CreatePublishing(PublishingDTO publishing)
        {
            var publishingEntity = mapper.Map<Publishing>(publishing);
            publishingEntity.PublishingEmployees = publishing.Employees
                .Select(e => new PublishingEmployee { Employeee = e.Id, PublishingId = publishing.Id }).ToList();
            unitOfWork.Publishings.Create(publishingEntity);
            unitOfWork.Save();
        }

        public void UpdatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<Publishing>(publishing);
            publishingEntity.PublishingEmployees = publishing.Employees
                .Select(e => new PublishingEmployee { Employeee = e.Id, PublishingId = publishing.Id }).ToList();
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
            IEnumerable<Topic> topics = unitOfWork.Topics.GetAll();
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
            IEnumerable<Topic> topics = unitOfWork.Topics.GetAll()
                .Where(t => !t.Publishings.Select(p => p.Id).Contains(publishingId));

            return mapper.Map<IEnumerable<TopicDTO>>(topics);
        }

        public IEnumerable<EmployeeDTO> GetEditorsNotInPublishing(int publishingId)
        {
            var publishing = unitOfWork.Publishings.Get(publishingId);
            return employeeService.GetEmployeeList("E", publishing.PublishingEmployees.Select(p => p.Employeee), true);
        }

        public IEnumerable<EmployeeDTO> GetJournalistsNotInPublishing(int publishingId)
        {
            var publishing = unitOfWork.Publishings.Get(publishingId);
           return  employeeService.GetEmployeeList("E", publishing.PublishingEmployees.Select(p => p.Employeee), true);
        }
    }
}
