using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.Repositories
{
    public class EmployeeRepository : IEmployeeRepository
    {
        private readonly EmployeeContext context;

        public EmployeeRepository()
        {
            context = new EmployeeContext();
        }

        public Employee Find(int id)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Id == id);
        }

        public IEnumerable<Employee> FindAll(
            Expression<Func<Employee, bool>> predicate)
        {
            return context.Employees.Include(x => x.Education).Where(predicate).OrderBy(x => x.Id).ToList();
        }

        public void Update(Employee entity)
        {
            if (entity.ChiefEditor)
            {
                var chiefEditor = context.Employees.FirstOrDefault(x => x.ChiefEditor);
                if (chiefEditor != null && chiefEditor.Id != entity.Id) chiefEditor.ChiefEditor = false;
            }

            context.Entry(entity).State = EntityState.Modified;
        }

        public void Delete(int id)
        {
            var employee = context.Employees.Find(id);
            context.Employees.Remove(employee ?? throw new InvalidOperationException());
        }

        public void Create(Employee entity)
        {
            if (entity.ChiefEditor)
            {
                var chiefEditor = context.Employees.FirstOrDefault(x => x.ChiefEditor);
                if (chiefEditor != null) chiefEditor.ChiefEditor = false;
            }

            context.Employees.Add(entity);
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public IEnumerable<Employee> GetEmployeeList()
        {
            return context.Employees.ToList();
        }

        public IEnumerable<Education> GetEducationList()
        {
            return context.Educations.ToList();
        }

        public int SaveChanges()
        {
            return context.SaveChanges();
        }
    }
}