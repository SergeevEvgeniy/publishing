using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Managers;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class EmployeeRepository : IEmployeeRepository
    {
        private readonly CloudPublishingContext context;

        public EmployeeRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        public IEnumerable<Employee> GetAll()
        {
            return context.Employees.Include(x => x.Education).AsNoTracking().ToList();
        }

        public Employee Get(int id)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Id == id);
        }

        public IEnumerable<Employee> Find(Func<Employee, bool> predicate)
        {
            return context.Employees.Include(x => x.Education).AsNoTracking().AsEnumerable().Where(predicate);
        }

        public void Create(Employee item)
        {
            context.Employees.Add(item);
        }

        public void Update(Employee item)
        {
            if (item.Password == null)
            {
                item.Password = context.Employees.AsNoTracking().FirstOrDefault(x => x.Id == item.Id)?.Password;
            }
            context.Entry(item).State = EntityState.Modified;
        }

        public void Delete(int id)
        {
            var entity = context.Employees.Find(id);
            if (entity != null) context.Employees.Remove(entity);
        }

        public IEnumerable<Education> GetEducationList()
        {
            return context.Educations.AsNoTracking().ToList();
        }
    }
}