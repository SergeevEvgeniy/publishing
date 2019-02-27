using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Identity.Stores
{
    public class EmployeeUserStore : IUserStore<EmployeeUser, int>
    {
        private readonly IMapper mapper;
        private readonly CloudPublishingContext context;

        public EmployeeUserStore(CloudPublishingContext context)
        {
            this.context = context;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeUserMapProfile())).CreateMapper();
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public Task CreateAsync(EmployeeUser user)
        {
            return Task.Run(() =>
            {
                context.Employees.Add(mapper.Map<EmployeeUser, Employee>(user));
                context.SaveChanges();
            });
            
        }

        public Task UpdateAsync(EmployeeUser user)
        {
            return Task.Run(() =>
            {
                var employee = mapper.Map<EmployeeUser, Employee>(user);
                context.Entry(employee).State = EntityState.Modified;
                context.SaveChanges();
            });
        }

        public Task DeleteAsync(EmployeeUser user)
        {
            return Task.Run(() =>
            {
                var employee = context.Employees.FirstOrDefault();
                if (employee != null)
                {
                    context.Employees.Remove(employee);
                }
                context.SaveChanges();
            });
        }

        public Task<EmployeeUser> FindByIdAsync(int userId)
        {
            return Task.Run(() =>
            {
                var user = context.Employees.Find(userId);
                return mapper.Map<Employee, EmployeeUser>(user);
            });
        }

        public Task<EmployeeUser> FindByNameAsync(string userName)
        {
            return Task.Run(() =>
            {
                var user = context.Employees.FirstOrDefault(x => x.Email == userName);
                return mapper.Map<Employee, EmployeeUser>(user);
            });
        }
    }
}