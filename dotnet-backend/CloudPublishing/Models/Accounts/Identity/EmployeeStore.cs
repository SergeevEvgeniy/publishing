using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Util;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Accounts.Identity
{
    public class EmployeeStore : IUserPasswordStore<EmployeeUser, int>, IQueryableUserStore<EmployeeUser, int>
    {
        private readonly EmployeeContext context;

        public EmployeeStore(EmployeeContext context)
        {
            this.context = context;
        }

        public void Dispose()
        {
            context?.Dispose();
        }

        public async Task CreateAsync(EmployeeUser user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeUser, Employee>(user);
            context.Employees.Add(employee);
            await context.SaveChangesAsync();
        }

        public async Task UpdateAsync(EmployeeUser user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeUser, Employee>(user);
            if (user.ChiefEditor)
            {
                var chiefEditor = await context.Employees.FirstOrDefaultAsync(x => x.ChiefEditor);
                if (chiefEditor != null) chiefEditor.ChiefEditor = false;
            }

            context.Entry(employee).State = EntityState.Modified;
            await context.SaveChangesAsync();
        }

        public Task DeleteAsync(EmployeeUser user)
        {
            var employee = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<EmployeeUser, Employee>(user);
            context.Entry(employee).State = EntityState.Deleted;
            return context.SaveChangesAsync();
        }

        public Task<EmployeeUser> FindByIdAsync(int userId)
        {
            return Task.Run(() =>
            {
                var employee = context.Employees.Include(x=>x.Education).FirstOrDefault(x=>x.Id == userId);
                return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<Employee, EmployeeUser>(employee);
            });
        }

        public Task<EmployeeUser> FindByNameAsync(string userName)
        {
            return Task.Run(() =>
            {
                var employee = context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Email == userName);
                return new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                    .Map<Employee, EmployeeUser>(employee);
            });
        }

        public Task SetPasswordHashAsync(EmployeeUser user, string passwordHash)
        {
            return Task.FromResult(user.PasswordHash = passwordHash);
        }

        public Task<string> GetPasswordHashAsync(EmployeeUser user)
        {
            return Task.FromResult(user.PasswordHash);
        }

        public Task<bool> HasPasswordAsync(EmployeeUser user)
        {
            return Task.FromResult(user.Password != null);
        }

        public IQueryable<EmployeeUser> Users =>
            new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<IQueryable<Employee>, List<EmployeeUser>>(context.Employees.Include(x=>x.Education)).AsQueryable();
    }
}