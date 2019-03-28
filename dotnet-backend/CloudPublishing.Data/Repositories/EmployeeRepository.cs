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
            return context.Employees.Include(x => x.Education).OrderBy(x => x.Id).AsNoTracking().ToList();
        }

        /// <inheritdoc />
        public Employee Get(int id)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Id == id);
        }

        /// <inheritdoc />
        public IEnumerable<Employee> Find(Func<Employee, bool> predicate)
        {
            return context.Employees.Include(x => x.Education).AsEnumerable().Where(predicate).ToList();
        }

        /// <inheritdoc />
        public void Create(Employee item)
        {
            context.Employees.Add(item);
        }

        /// <inheritdoc />
        public void Update(Employee item)
        {
            context.Entry(item).State = EntityState.Modified;

            if (item.Password == null)
            {
                context.Entry(item).Property(x => x.Password).IsModified = false;
            }
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