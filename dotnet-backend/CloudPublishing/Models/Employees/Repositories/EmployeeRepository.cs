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
    public class EmployeeRepository : IAsyncRepository<Employee>
    {
        private readonly EmployeeContext context;

        public EmployeeRepository(EmployeeContext context)
        {
            this.context = context;
        }

        public async Task<Employee> FindAsync(int id)
        {
            return await context.Employees.Include(x => x.Education).FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<IEnumerable<Employee>> FindAllAsync(
            Expression<Func<Employee, bool>> predicate)
        {
            return await context.Employees.Include(x => x.Education).Where(predicate).OrderBy(x => x.Id).ToListAsync();
        }

        public async Task<int> Update(Employee entity)
        {
            if (entity.ChiefEditor)
            {
                var chiefEditor = await context.Employees.FirstOrDefaultAsync(x => x.ChiefEditor);
                if (chiefEditor != null && chiefEditor.Id != entity.Id) chiefEditor.ChiefEditor = false;
            }

            context.Entry(entity).State = EntityState.Modified;
            return await context.SaveChangesAsync();
        }

        public async Task<int> Delete(int id)
        {
            var employee = await context.Employees.FindAsync(id);
            context.Employees.Remove(employee ?? throw new InvalidOperationException());
            return await context.SaveChangesAsync();
        }

        public async Task<Employee> Create(Employee entity)
        {
            if (entity.ChiefEditor)
            {
                var chiefEditor = await context.Employees.Where(x => x.ChiefEditor).FirstOrDefaultAsync();
                if (chiefEditor != null) chiefEditor.ChiefEditor = false;
            }

            context.Employees.Add(entity);
            await context.SaveChangesAsync();
            return entity;
        }
    }
}