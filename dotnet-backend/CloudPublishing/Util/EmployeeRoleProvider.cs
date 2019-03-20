using System;
using System.Web.Security;
using CloudPublishing.Business.Services;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.Util
{
    /// <inheritdoc />
    /// <summary>
    ///     Предоставляет функционал по работе с ролями пользователей
    /// </summary>
    public class EmployeeRoleProvider : RoleProvider
    {
        private readonly IRoleService service;

        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр класса используя <see cref="RoleService"/>
        /// </summary>
        public EmployeeRoleProvider()
        {
            service = new RoleService();
        }

        /// <inheritdoc />
        public override string ApplicationName { get; set; }

        /// <inheritdoc />
        public override bool IsUserInRole(string username, string roleName)
        {
            return service.IsUserInRole(username, roleName);
        }

        /// <inheritdoc />
        public override string[] GetRolesForUser(string username)
        {
            return service.GetRolesForUser(username);
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override void CreateRole(string roleName)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override bool DeleteRole(string roleName, bool throwOnPopulatedRole)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override bool RoleExists(string roleName)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override void AddUsersToRoles(string[] usernames, string[] roleNames)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override void RemoveUsersFromRoles(string[] usernames, string[] roleNames)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override string[] GetUsersInRole(string roleName)
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override string[] GetAllRoles()
        {
            throw new NotImplementedException();
        }

        /// <inheritdoc />
        /// <exception cref="NotImplementedException"></exception>
        public override string[] FindUsersInRole(string roleName, string usernameToMatch)
        {
            throw new NotImplementedException();
        }
    }
}