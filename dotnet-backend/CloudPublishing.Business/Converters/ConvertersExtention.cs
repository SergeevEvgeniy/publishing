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

        public static EmployeeDTO ToDTO(this Employee e)
        {
            return new EmployeeDTO
            {
                Id = e.Id,
                FirstName = e.FirstName,
                MiddleName = e.MiddleName,
                LastName = e.LastName,
                Address = e.Address,
                BirthYear = e.BirthYear,
                ChiefEditor = e.ChiefEditor,
                Education = e.Education != null ? e.Education.ToDTO() : null,
                EducationId = e.EducationId,
                Email = e.Email,
                Password = e.Password,
                Sex = e.Sex,
                Type = e.Type
            };
        }

        public static Employee ToEntity(this EmployeeDTO e)
        {
            return new Employee
            {
                Id = e.Id,
                FirstName = e.FirstName,
                MiddleName = e.MiddleName,
                LastName = e.LastName,
                Address = e.Address,
                BirthYear = e.BirthYear,
                ChiefEditor = e.ChiefEditor,
                Education = e.Education != null ? e.Education.ToEntity() : null,
                EducationId = e.EducationId,
                Email = e.Email,
                Password = e.Password,
                Sex = e.Sex,
                Type = e.Type
            };
        }

        public static EducationDTO ToDTO(this Education ed)
        {
            return new EducationDTO
            {
                Id = ed.Id,
                Title = ed.Title
            };
        }

        public static Education ToEntity(this EducationDTO ed)
        {
            return new Education
            {
                Id = ed.Id,
                Title = ed.Title
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
                Topics = publishingDTO.Topics == null ? null : publishingDTO.Topics.Select(x => x.ToEntity()).ToList(),
                Employees = publishingDTO.Employees == null ? null : publishingDTO.Employees.Select(x => x.ToEntity()).ToList()
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
                Topics = publishingEntity.Topics.Select(x => x.ToDTO()).ToList(),
                Employees = publishingEntity.Employees.Select(x => x.ToDTO()).ToList()
            };
        }
    }
}
