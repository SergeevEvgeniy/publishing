using CloudPublishing.Business.Converters;
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

        public PublishingService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
        }

        public IEnumerable<PublishingDTO> GetAllPublishings()
        {
            return unitOfWork.Publishings.GetAll().Select(x => x.ToDTO());
        }

        public IEnumerable<EmployeeDTO> GetPublishingEmployees(int publishingId)
        {
            return unitOfWork.Publishings.Get(publishingId).Employees.Select(x => x.ToDTO());
        }

        public PublishingDTO GetPublishing(int id)
        {
            PublishingDTO mappedPublishing = null;
            Publishing publishing = unitOfWork.Publishings.Get(id);

            if (publishing != null)
            {
                mappedPublishing = publishing.ToDTO();
            }

            return mappedPublishing;
        }

        public void CreatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = publishing.ToEntity();
            unitOfWork.Publishings.Create(publishingEntity);
        }

        public void UpdatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = publishing.ToEntity();
            unitOfWork.Publishings.Update(publishingEntity);
        }

        public void DeletePublishing(int id)
        {
            unitOfWork.Publishings.Delete(id);
        }

        public IEnumerable<TopicDTO> GetAllTopics()
        {
            return unitOfWork.Topics.GetAll().Select(x => x.ToDTO());
        }

        public IEnumerable<EmployeeDTO> GetAllEmployees()
        {
            return unitOfWork.Employees.GetAll().Select(x => x.ToDTO());
        }

        public IEnumerable<EmployeeDTO> GetEmployeesNotInPublishing(int publishingId)
        {
            return unitOfWork.Employees.GetAll()
                   .Where(e => !e.Publishings.Select(p => p.Id).Contains(publishingId))
                   .Select(x => x.ToDTO());
        }

        public IEnumerable<TopicDTO> GetTopicsNotInPublishing(int publishingId)
        {
            return unitOfWork.Topics.GetAll()
                .Where(t => !t.Publishings.Select(p => p.Id).Contains(publishingId))
                .Select(x => x.ToDTO());
        }
    }
}
