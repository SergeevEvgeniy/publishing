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
    public class EducationRepository : IAsyncRepository<Education>
    {
        private readonly EmployeeContext context;

        public EducationRepository(EmployeeContext context)
        {
            this.context = context;
        }

        public async Task<Education> FindAsync(int id)
        {
            return await context.Educations.FindAsync(id);
        }

        public async Task<IEnumerable<Education>> FindAllAsync(Expression<Func<Education, bool>> predicate)
        {
            return await context.Educations.Where(predicate).OrderBy(x => x.Id).ToListAsync();
        }

        public async Task Update(Education entity)
        {
            context.Entry(entity).State = EntityState.Modified;
            await context.SaveChangesAsync();
        }

        public async Task Delete(int id)
        {
            var education = await context.Educations.FindAsync(id);
            context.Educations.Remove(education ?? throw new InvalidOperationException());
        }

        public async Task<Education> Create(Education entity)
        {
            context.Educations.Add(entity);
            await context.SaveChangesAsync();
            return entity;
        }
    }
}