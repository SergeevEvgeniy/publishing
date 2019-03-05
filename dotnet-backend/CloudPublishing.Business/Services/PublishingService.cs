using AutoMapper;
using CloudPublishing.Business.Converters;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Business.Util;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Business.Services
{
    public class PublishingService : IPublishingService
    {
        private readonly IUnitOfWork unitOfWork;
        private IMapper mapper;

        public PublishingService(IUnitOfWork unitOfWork)
        {
            this.unitOfWork = unitOfWork;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new PublishingBusinessMapProfile())).CreateMapper();
        }

        public IEnumerable<PublishingDTO> GetAllPublishings()
        {
            return mapper.Map<IEnumerable<Publishing>, IEnumerable<PublishingDTO>>(unitOfWork.Publishings.GetAll());
        }

        public PublishingDTO GetPublishing(int id)
        {
            PublishingDTO mappedPublishing = null;
            Publishing publishing = unitOfWork.Publishings.Get(id);

            if (publishing != null)
            { 
                mappedPublishing = mapper.Map<Publishing, PublishingDTO>(publishing);
            }

            return mappedPublishing;
        }

        public void CreatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<PublishingDTO, Publishing>(publishing);
            unitOfWork.Publishings.Create(publishingEntity);
        }

        public void UpdatePublishing(PublishingDTO publishing)
        {
            Publishing publishingEntity = mapper.Map<PublishingDTO, Publishing>(publishing);
            unitOfWork.Publishings.Update(publishingEntity);
        }

        public void DeletePublishing(int id)
        {
            unitOfWork.Publishings.Delete(id);
        }

        public IEnumerable<TopicDTO> GetAllTopics()
        {
            return mapper.Map<IEnumerable<Topic>, IEnumerable<TopicDTO>>(unitOfWork.Topics.GetAll());
        }
    }
}
