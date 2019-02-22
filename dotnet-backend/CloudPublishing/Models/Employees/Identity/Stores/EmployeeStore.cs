using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.EF;
using CloudPublishing.Models.Employees.Entities;
using CloudPublishing.Models.Employees.Enums;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Employees.Identity.Stores
{
    public class EmployeeStore : IUserPasswordStore<Employee, int>, IUserRoleStore<Employee, int>
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

        public async Task CreateAsync(Employee user)
        {
            if (user.ChiefEditor)
            {
                var chiefEditor = await context.Employees.FirstOrDefaultAsync(x => x.ChiefEditor);
                if (chiefEditor != null) chiefEditor.ChiefEditor = false;
            }

            context.Employees.Add(user);
            await context.SaveChangesAsync();
        }

        public async Task UpdateAsync(Employee user)
        {
            var chiefEditor = await context.Employees.FirstOrDefaultAsync(x => x.ChiefEditor);
            if (chiefEditor != null && chiefEditor.Id != user.Id && user.ChiefEditor) chiefEditor.ChiefEditor = false;

            context.Entry(user).State = EntityState.Modified;

            await context.SaveChangesAsync();
        }

        public Task DeleteAsync(Employee user)
        {
            context.Entry(user).State = EntityState.Deleted;
            return context.SaveChangesAsync();
        }

        public Task<Employee> FindByIdAsync(int userId)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefaultAsync(x => x.Id == userId);
        }

        public Task<Employee> FindByNameAsync(string userName)
        {
            return context.Employees.Include(x => x.Education).FirstOrDefaultAsync(x => x.UserName == userName);
        }

        public Task SetPasswordHashAsync(Employee user, string passwordHash)
        {
            return Task.FromResult(user.PasswordHash = passwordHash);
        }

        public async Task<string> GetPasswordHashAsync(Employee user)
        {
            return (await context.Employees.AsNoTracking().FirstOrDefaultAsync(x => x.Id == user.Id))?.PasswordHash;
        }

        public async Task<bool> HasPasswordAsync(Employee user)
        {
            return (await context.Employees.AsNoTracking().FirstOrDefaultAsync(x => x.Id == user.Id))?.PasswordHash !=
                   null;
        }

        public Task AddToRoleAsync(Employee user, string roleName)
        {
            return Task.FromResult<object>(null);
        }

        public Task RemoveFromRoleAsync(Employee user, string roleName)
        {
            return Task.FromResult<object>(null);
        }

        public Task<IList<string>> GetRolesAsync(Employee user)
        {
            IList<string> result = new List<string>();
            if (user.ChiefEditor) result.Add(EmployeeUserRole.ChiefEditor.ToString());
            if (user.Type == "J")
                result.Add(EmployeeUserRole.Journalist.ToString());
            else if (user.Type == "E") result.Add(EmployeeUserRole.Editor.ToString());

            return Task.FromResult(result);
        }

        public Task<bool> IsInRoleAsync(Employee user, string roleName)
        {
            var roleIsValid = Enum.TryParse(roleName, out EmployeeUserRole role);
            if (!roleIsValid) return Task.FromResult(false);
            var roles = new Dictionary<EmployeeUserRole, bool>
            {
                {EmployeeUserRole.ChiefEditor, user.ChiefEditor},
                {EmployeeUserRole.Editor, user.Type == "E"},
                {EmployeeUserRole.Journalist, user.Type == "J"}
            };

            return Task.FromResult(roles[role]);
        }
    }
}