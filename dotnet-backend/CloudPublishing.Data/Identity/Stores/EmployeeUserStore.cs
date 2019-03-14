using AutoMapper;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Entities;
using CloudPublishing.Data.Identity.Entities;
using CloudPublishing.Data.Identity.Enums;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Identity.Stores
{
    public class EmployeeUserStore : IUserPasswordStore<EmployeeUser, int>, IUserRoleStore<EmployeeUser, int>
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
                var employee = mapper.Map<EmployeeUser, Employee>(user);
                if (employee.ChiefEditor)
                {
                    var chief = context.Employees.FirstOrDefault(x => x.ChiefEditor);
                    if (chief != null)
                    {
                        chief.ChiefEditor = false;
                    }
                }
                context.Employees.Add(mapper.Map<EmployeeUser, Employee>(user));
                context.SaveChanges();
            });
        }

        public async Task UpdateAsync(EmployeeUser user)
        {
            var employee = mapper.Map<EmployeeUser, Employee>(user);
            var chiefEditor = context.Employees.AsNoTracking().FirstOrDefault(x => x.ChiefEditor);
            if (chiefEditor != null && chiefEditor.Id != employee.Id && employee.ChiefEditor) chiefEditor.ChiefEditor = false;
            if (chiefEditor != null && chiefEditor?.Id != employee.Id)
            {
                context.Entry(chiefEditor).State = EntityState.Modified;
            }
            context.Entry(employee).State = EntityState.Modified;
            await context.SaveChangesAsync();
        }

        public Task DeleteAsync(EmployeeUser user)
        {
            var employee = mapper.Map<EmployeeUser, Employee>(user);
            context.Entry(employee).State = EntityState.Deleted;
            return context.SaveChangesAsync();
        }

        public Task<EmployeeUser> FindByIdAsync(int userId)
        {
            return Task.Run(() =>
            {
                var user = context.Employees.AsNoTracking().FirstOrDefault(x=> x.Id == userId);
                return mapper.Map<Employee, EmployeeUser>(user);
            });
        }

        public Task<EmployeeUser> FindByNameAsync(string userName)
        {
            return Task.Run(() =>
            {
                var user = context.Employees.AsNoTracking().FirstOrDefault(x => x.Email == userName);
                return mapper.Map<Employee, EmployeeUser>(user);
            });
        }

        public Task SetPasswordHashAsync(EmployeeUser user, string passwordHash)
        {
            return Task.FromResult(user.PasswordHash = passwordHash);
        }

        public Task<string> GetPasswordHashAsync(EmployeeUser user)
        {
            return Task.Run(() => context.Employees.AsNoTracking().FirstOrDefault(x => x.Id == user.Id)?.Password);
        }

        public Task<bool> HasPasswordAsync(EmployeeUser user)
        {
            return Task.FromResult(user.Password != null);
        }

        public Task AddToRoleAsync(EmployeeUser user, string roleName)
        {
            throw new System.NotImplementedException();
        }

        public Task RemoveFromRoleAsync(EmployeeUser user, string roleName)
        {
            throw new System.NotImplementedException();
        }

        public Task<IList<string>> GetRolesAsync(EmployeeUser user)
        {
            var list = new List<string> {user.Type == "E" ? Role.Editor.ToString() : Role.Journalist.ToString()};
            if (user.ChiefEditor)
            {
                list.Add(Role.ChiefEditor.ToString());
            }

            return Task.FromResult((IList<string>)list);

        }

        public Task<bool> IsInRoleAsync(EmployeeUser user, string roleName)
        {
            var userRoles = new Dictionary<Role, bool>
            {
                {Role.ChiefEditor, user.ChiefEditor},
                {Role.Editor, user.Type == "E"},
                {Role.Journalist, user.Type == "J"}
            };

            return Task.FromResult(Enum.TryParse(roleName, out Role role) && userRoles[role]);
        }
    }
}