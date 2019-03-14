using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Data.Repositories
{
    public class PublishingRepository : IPublishingRepository
    {
        private readonly CloudPublishingContext context;

        public PublishingRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public Publishing Get(int id)
        {
            return context.Publishings.AsNoTracking()
                .Include("Topics").AsNoTracking()
                .Include("Employees").AsNoTracking()
                .FirstOrDefault(x => x.Id == id);
        }

        public IEnumerable<Publishing> GetAll()
        {
            return context.Publishings.AsNoTracking()
                .Include("Topics").AsNoTracking()
                .Include("Employees").AsNoTracking()
                .ToList();
        }

        public void Create(Publishing publishing)
        {
            context.Publishings.Add(MapToRealPublishing(publishing));
            context.SaveChanges();
        }

        public void Update(Publishing modifyPublishing)
        {
            Publishing publishingToUpdate = context.Publishings
                .Include("Employees")
                .Include("Topics")
                .FirstOrDefault(x => x.Id == modifyPublishing.Id);

            if (publishingToUpdate == null)
            {
                throw new Exception($"User with Id '{modifyPublishing.Id}' not found");
            }

            publishingToUpdate.Title = modifyPublishing.Title;
            publishingToUpdate.Type = modifyPublishing.Type;
            publishingToUpdate.Subjects = modifyPublishing.Subjects;
            UpdatePublishingEmployees(publishingToUpdate, modifyPublishing.Employees);
            UpdatePublishingTopics(publishingToUpdate, modifyPublishing.Topics);
            context.SaveChanges();
        }

        public void Delete(int id)
        {
            var publishing = context.Publishings.FirstOrDefault(x => x.Id == id);
            if (publishing != null)
            {
                context.Publishings.Remove(publishing);
                context.SaveChanges();
            }
        }

        private Publishing MapToRealPublishing(Publishing publishing)
        {
            publishing.Employees = MapToRealEmployees(publishing.Employees);
            publishing.Topics = MapToRealTopics(publishing.Topics);
            return publishing;
        }

        private ICollection<Employee> MapToRealEmployees(ICollection<Employee> employees)
        {
            var realPublishingEmployees = new List<Employee>();

            if (employees != null)
            {
                foreach (var employee in employees)
                {
                    var realEmployee = context.Employees.FirstOrDefault(x => x.Id == employee.Id);
                    if (realEmployee == null)
                    {
                        throw new Exception();
                    }
                    realPublishingEmployees.Add(realEmployee);
                }
            }
            return realPublishingEmployees;
        }

        private ICollection<Topic> MapToRealTopics(ICollection<Topic> topics)
        {
            var realTopics = new List<Topic>();
            if (topics != null)
            {


                foreach (var topic in topics)
                {
                    var realTopic = context.Topics.FirstOrDefault(x => x.Id == topic.Id);
                    if (realTopic == null)
                    {
                        throw new Exception();
                    }
                    realTopics.Add(realTopic);
                }
            }
            return realTopics;
        }

        private void UpdatePublishingEmployees(Publishing publishingToUpdate, ICollection<Employee> selectedEmployee)
        {
            if (selectedEmployee == null)
            {
                publishingToUpdate.Employees = new List<Employee>();
                return;
            }

            foreach (var employee in context.Employees)
            {
                if (selectedEmployee.Select(x => x.Id).Contains(employee.Id))
                {
                    if (!publishingToUpdate.Employees.Select(x => x.Id).Contains(employee.Id))
                    {
                        publishingToUpdate.Employees.Add(employee);
                    }
                }
                else
                {
                    if (publishingToUpdate.Employees.Select(x => x.Id).Contains(employee.Id))
                    {
                        publishingToUpdate.Employees.Remove(employee);
                    }
                }
            }
        }

        private void UpdatePublishingTopics(Publishing publishingToUpdate, ICollection<Topic> selectedTopics)
        {
            if (selectedTopics == null)
            {
                publishingToUpdate.Topics = new List<Topic>();
                return;
            }

            foreach (var topic in context.Topics)
            {
                if (selectedTopics.Select(x => x.Id).Contains(topic.Id))
                {
                    if (!publishingToUpdate.Topics.Select(x => x.Id).Contains(topic.Id))
                    {
                        publishingToUpdate.Topics.Add(topic);
                    }
                }
                else
                {
                    if (publishingToUpdate.Topics.Select(x => x.Id).Contains(topic.Id))
                    {
                        publishingToUpdate.Topics.Remove(topic);
                    }
                }
            }


        }

    }
}
