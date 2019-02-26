using CloudPublishing.Business.DTO;
using CloudPublishing.Data.Entities;
using System.Linq;

namespace CloudPublishing.Business.Converters
{
    static class ConvertersExtention
    {
        public static TopicDTO ToDTO(this Topic topicEntity)
        {
            return new TopicDTO
            {
                Id = topicEntity.Id,
                Name = topicEntity.Name
            };
        }

        public static Topic ToEntity(this TopicDTO topicDTO)
        {
            return new Topic
            {
                Id = topicDTO.Id,
                Name = topicDTO.Name
            };
        }

        public static Publishing ToEntity(this PublishingDTO publishingDTO)
        {
            return new Publishing
            {
                Id = publishingDTO.Id,
                Title = publishingDTO.Title,
                Type = publishingDTO.Type,
                Subjects = publishingDTO.Subjects,
                Topics = publishingDTO.Topics.Select(x => x.ToEntity()).ToList()
            };
        }

        public static PublishingDTO ToDTO(this Publishing publishingEntity)
        {
            return new PublishingDTO
            {
                Id = publishingEntity.Id,
                Title = publishingEntity.Title,
                Type = publishingEntity.Type,
                Subjects = publishingEntity.Subjects,
                Topics = publishingEntity.Topics.Select(x => x.ToDTO()).ToList()
            };
        }
    }
}
