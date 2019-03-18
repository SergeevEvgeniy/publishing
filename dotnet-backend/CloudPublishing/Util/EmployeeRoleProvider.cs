using System;
using System.Web.Security;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.Util
{
    public class EmployeeRoleProvider : RoleProvider
    {
        private readonly IRoleService service;

        public EmployeeRoleProvider()
        {
            service = new RoleService();
        }

        public override string ApplicationName { get; set; }

        public override bool IsUserInRole(string username, string roleName)
        {
            return service.IsUserInRole(username, roleName);
        }

        public override string[] GetRolesForUser(string username)
        {
            return service.GetRolesForUser(username);
        }

        public override void CreateRole(string roleName)
        {
            throw new NotImplementedException();
        }

        public override bool DeleteRole(string roleName, bool throwOnPopulatedRole)
        {
            throw new NotImplementedException();
        }

        public override bool RoleExists(string roleName)
        {
            throw new NotImplementedException();
        }

        public override void AddUsersToRoles(string[] usernames, string[] roleNames)
        {
            throw new NotImplementedException();
        }

        public override void RemoveUsersFromRoles(string[] usernames, string[] roleNames)
        {
            throw new NotImplementedException();
        }

        public override string[] GetUsersInRole(string roleName)
        {
            throw new NotImplementedException();
        }

        public override string[] GetAllRoles()
        {
            throw new NotImplementedException();
        }

        public override string[] FindUsersInRole(string roleName, string usernameToMatch)
        {
            throw new NotImplementedException();
        }
    }
}