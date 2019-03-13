using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Converters
{
    static class UIConvertersExtension
    {
        public static PublishingDTO ToDTO(this PublishingViewModel publishing)
        {
            return new PublishingDTO
            {
                Id = publishing.Id,
                Title = publishing.Title,
                Subjects = publishing.Subjects,
                Type = publishing.Type,
                Topics = publishing.TopicsId == null ? null : publishing.TopicsId.Select(x => new TopicDTO { Id = x }),
                Employees = publishing.EmployeesId == null ? null : publishing.EmployeesId.Select(x => new EmployeeDTO { Id = x })

            };
        }

        public static TopicDTO ToDTO(this TopicViewModel topic)
        {
            return new TopicDTO
            {
                Id = topic.Id
            };
        }

        public static EmployeeDTO ToDTO(this PublishingEmployeeViewModel employee)
        {
            return new EmployeeDTO
            {
                Id = employee.Id
            };
        }

        public static PublishingViewModel ToViewModel(this PublishingDTO publishing)
        {
            return new PublishingViewModel
            {
                Id = publishing.Id,
                Title = publishing.Title,
                Type = publishing.Type,
                Subjects = publishing.Subjects,
                TopicsId = publishing.Topics.Select(x => x.Id),
                EmployeesId = publishing.Employees.Select(x => x.Id)
            };
        }

        public static TopicViewModel ToViewModel(this TopicDTO topic)
        {
            return new TopicViewModel
            {
                Id = topic.Id,
                Name = topic.Name,
            };
        }

        public static PublishingEmployeeViewModel ToViewModel(this EmployeeDTO employee)
        {
            string shortName = employee.FirstName + ' ' + employee.LastName[0] + '.';

            if (employee.MiddleName != null)
            {
                shortName = shortName + employee.MiddleName[0] + '.';
            }

            return new PublishingEmployeeViewModel
            {
                Id = employee.Id,
                Type = employee.Type,
                Name = shortName
            };
        }

    }
}