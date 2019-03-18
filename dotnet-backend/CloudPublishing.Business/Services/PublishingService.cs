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
        public PublishingService(IUnitOfWork unitOfWork, IMapper mapper)
        {
            this.unitOfWork = unitOfWork;
            this.mapper = mapper;
        }

        public IEnumerable<PublishingDTO> GetAllPublishings()
        {
            IEnumerable<Publishing> publishings = unitOfWork.Publishings.GetAll();
            return mapper.Map<IEnumerable<PublishingDTO>>(publishings);
        }

        public IEnumerable<EmployeeDTO> GetPublishingEmployees(int publishingId)
        {
            IEnumerable<Employee> employees = unitOfWork.Publishings.Get(publishingId).Employees;
            return mapper.Map<IEnumerable<EmployeeDTO>>(employees);
        }

        public PublishingDTO GetPublishing(int id)
        {
            Publishing publishing = unitOfWork.Publishings.Get(id);
            return mapper.Map<PublishingDTO>(publishing);
        }

        public void CreatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<Publishing>(publishing);
            unitOfWork.Publishings.Create(publishingEntity);
            unitOfWork.Save();
        }

        public void UpdatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<Publishing>(publishing);
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

        public IEnumerable<EmployeeDTO> GetAllEmployees()
        {
            IEnumerable<Employee> employees = unitOfWork.Employees.GetAll();
            return mapper.Map<IEnumerable<EmployeeDTO>>(employees);
        }

        public IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId)
        {
            IEnumerable<Topic> topics = unitOfWork.Topics.GetAll()
                .Where(t => !t.Publishings.Select(p => p.Id).Contains(publishingId));

            return mapper.Map<IEnumerable<TopicDTO>>(topics);
        }

        public IEnumerable<EmployeeDTO> GetEmployeesNotInPublishing(int publishingId)
        {
            IEnumerable<Employee> employees = unitOfWork.Employees.GetAll()
                   .Where(e => !e.Publishings.Select(p => p.Id).Contains(publishingId));

            return mapper.Map<IEnumerable<EmployeeDTO>>(employees);
        }
    }
}
