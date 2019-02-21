using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Identity.Entities;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Models.Employees.Identity.Stores
{
    public class EmployeeRoleStore : IRoleStore<EmployeeRole, int>
    {
        private readonly IEnumerable<EmployeeRole> roles;

        public EmployeeRoleStore()
        {
            roles = new List<EmployeeRole>
            {
                new EmployeeRole{Id = (int)EmployeeUserRole.ChiefEditor, Name = EmployeeUserRole.ChiefEditor.ToString()},
                new EmployeeRole{Id = (int)EmployeeUserRole.Editor, Name = EmployeeUserRole.Editor.ToString()},
                new EmployeeRole{Id = (int)EmployeeUserRole.Journalist, Name = EmployeeUserRole.Journalist.ToString()}
            };
        }

        public void Dispose()
        {
            
        }

        public Task CreateAsync(EmployeeRole role)
        {
            return Task.FromResult<object>(null);
        }

        public Task UpdateAsync(EmployeeRole role)
        {
            return Task.FromResult<object>(null);
        }

        public Task DeleteAsync(EmployeeRole role)
        {
            return Task.FromResult<object>(null);
        }

        public Task<EmployeeRole> FindByIdAsync(int roleId)
        {
            return Task.FromResult(roles.FirstOrDefault(x=>x.Id == roleId));
        }

        public Task<EmployeeRole> FindByNameAsync(string roleName)
        {
            return Task.FromResult(roles.FirstOrDefault(x => x.Name == roleName));
        }
    }
}