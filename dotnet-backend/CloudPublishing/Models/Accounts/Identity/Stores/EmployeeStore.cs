using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CloudPublishing.Models.Accounts.Enums;
using CloudPublishing.Models.Accounts.Identity.Entities;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Util;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Accounts.Identity.Stores
{
    public class EmployeeStore : IUserPasswordStore<EmployeeUser, int>, IQueryableUserStore<EmployeeUser, int>,
        IUserRoleStore<EmployeeUser, int>
    {
        private readonly EmployeeContext context;

        public EmployeeStore(EmployeeContext context)
        {
            this.context = context;
        }

        public IQueryable<EmployeeUser> Users =>
            new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper()
                .Map<IQueryable<Employee>, List<EmployeeUser>>(context.Employees.Include(x => x.Education))
                .AsQueryable();

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
                var employee = context.Employees.Include(x => x.Education).FirstOrDefault(x => x.Id == userId);
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

        public Task AddToRoleAsync(EmployeeUser user, string roleName)
        {
            return Task.FromResult<object>(null);
        }

        public Task RemoveFromRoleAsync(EmployeeUser user, string roleName)
        {
            return Task.FromResult<object>(null);
        }

        public Task<IList<string>> GetRolesAsync(EmployeeUser user)
        {
            IList<string> result = new List<string>();
            if (user.ChiefEditor) result.Add(EmployeeUserRole.ChiefEditor.ToString());
            if (user.Type == "J")
                result.Add(EmployeeUserRole.Journalist.ToString());
            else if (user.Type == "E") result.Add(EmployeeUserRole.Editor.ToString());

            return Task.FromResult(result);
        }

        public Task<bool> IsInRoleAsync(EmployeeUser user, string roleName)
        {
            var result = Task.FromResult(false);
            if (!Enum.TryParse(roleName, out EmployeeUserRole role)) return result;
            switch (role)
            {
                case EmployeeUserRole.ChiefEditor:
                    result = Task.FromResult(user.ChiefEditor);
                    break;
                case EmployeeUserRole.Editor:
                    result = Task.FromResult(user.Type == "E");
                    break;
                case EmployeeUserRole.Journalist:
                    result = Task.FromResult(user.Type == "J");
                    break;
                default:
                    result = Task.FromResult(false);
                    break;
            }

            return result;
        }
    }
}