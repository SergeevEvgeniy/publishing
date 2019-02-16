using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Repositories.Interfaces;

namespace CloudPublishing.Models.Employees.Repositories
{
    public class EducationRepository : IRepository<Education>
    {
        private readonly EmployeeContext context;

        public EducationRepository(EmployeeContext context)
        {
            this.context = context;
        }

        public Education Find(int id)
        {
            return context.Educations.Find(id);
        }

        public IEnumerable<Education> FindAll(Expression<Func<Education, bool>> predicate)
        {
            return context.Educations.Where(predicate).OrderBy(x => x.Id).ToList();
        }

        public void Update(Education entity)
        {
            context.Entry(entity).State = EntityState.Modified;
        }

        public void Delete(int id)
        {
            var education = context.Educations.Find(id);
            context.Educations.Remove(education ?? throw new InvalidOperationException());
        }

        public void Create(Education entity)
        {
            context.Educations.Add(entity);
        }
    }
}