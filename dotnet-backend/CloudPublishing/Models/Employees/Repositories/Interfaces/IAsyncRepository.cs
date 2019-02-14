using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Entities;

namespace CloudPublishing.Models.Employees.Repositories.Interfaces
{
    public interface IAsyncRepository<T> where T : BasicEntity
    {
        Task<T> FindAsync(int id);

        Task<IEnumerable<T>> FindAllAsync(Expression<Func<T, bool>> predicate);

        Task<int> Update(T entity);

        Task<int> Delete(int id);

        Task<T> Create(T entity);
    }
}