using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using System;
using System.Collections.Generic;
using CloudPublishing.Data.Interfaces;
using System.Linq;
using CloudPublishing.Business.Converters;
using System.Text;
using System.Threading.Tasks;

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

        public IEnumerable<TopicDTO> GetAllTopics()
        {
            return unitOfWork.Topics.GetAll().Select(x => x.ToDTO());
        }
    }
}
