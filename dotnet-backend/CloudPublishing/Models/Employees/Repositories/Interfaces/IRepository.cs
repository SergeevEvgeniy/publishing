using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Entities;

namespace CloudPublishing.Models.Employees.Repositories.Interfaces
{
    public interface IRepository<T> where T : BasicEntity
    {
        T Find(int id);

        IEnumerable<T> FindAll(Expression<Func<T, bool>> predicate);

        void Update(T entity);

        void Delete(int id);

        void Create(T entity);
    }
}