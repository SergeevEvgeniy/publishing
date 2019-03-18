namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IRoleService
    {
        bool IsUserInRole(string email, string roleName);

        string[] GetRolesForUser(string email);
    }
}