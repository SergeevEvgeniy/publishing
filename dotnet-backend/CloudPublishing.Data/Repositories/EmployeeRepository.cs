using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;

namespace CloudPublishing.Data.Repositories
{
    /// <inheritdoc />
    public class EmployeeRepository : IEmployeeRepository
    {
        private readonly CloudPublishingContext context;

        /// <summary>
        /// Создает экземпляр репозитория из контекста работы с базой данных
        /// </summary>
        /// <param name="context">Контекст работы с базой данных</param>
        public EmployeeRepository(CloudPublishingContext context)
        {
            this.context = context;
        }

        /// <inheritdoc />
        public IEnumerable<Employee> GetAll()
        {
            return context.Employees.Include(x => x.Education).Include("Publishings").AsNoTracking().AsEnumerable();
        }

        /// <inheritdoc />
        public Employee Get(int id)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Id == id);
        }

        /// <inheritdoc />
        public IEnumerable<Employee> Find(Func<Employee, bool> predicate)
        {
            return context.Employees.Include(x => x.Education).AsEnumerable().Where(predicate);
        }

        /// <inheritdoc />
        public void Create(Employee item)
        {
            context.Employees.Add(item);
        }

        /// <inheritdoc />
        public void Update(Employee item)
        {
            if (item.Password == null)
            {
                item.Password = context.Employees.AsNoTracking().FirstOrDefault(x => x.Id == item.Id)?.Password;
            }

            var entry = context.Entry(item);
            if (entry.State != EntityState.Detached && entry.State != EntityState.Modified)
            {
                return;
            }

            entry.State = EntityState.Modified;
        }

        /// <inheritdoc />
        public void Delete(int id)
        {
            var entity = context.Employees.Find(id);
            if (entity != null)
            {
                context.Employees.Remove(entity);
            }
        }

        /// <inheritdoc />
        public IEnumerable<Education> GetEducationList()
        {
            return context.Educations.AsNoTracking().ToList();
        }
    }
}