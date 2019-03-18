using System.Collections.Generic;
using System.Linq;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Data.EF;
using CloudPublishing.Data.Interfaces;
using CloudPublishing.Data.Repositories;

namespace CloudPublishing.Business.Services
{
    public class RoleService : IRoleService
    {
        private readonly IUnitOfWork unit;

        public RoleService()
        {
            unit = new UnitOfWork(new CloudPublishingContext("EmployeeContext"));
        }

        public bool IsUserInRole(string email, string roleName)
        {
            return GetRolesForUser(email).Contains(roleName);
        }

        public string[] GetRolesForUser(string email)
        {
            var user = unit.Employees.Find(x => x.Email == email).FirstOrDefault();
            if (user == null)
            {
                throw new EntityNotFoundException("Пользователь не найден");
            }

            var roles = new List<string>();

            if (user.ChiefEditor)
            {
                roles.Add("ChiefEditor");
            }

            roles.Add(user.Type == "E" ? "Editor" : "Journalist");

            return roles.ToArray();
        }
    }
}