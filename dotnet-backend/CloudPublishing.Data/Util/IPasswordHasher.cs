using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Util
{
    public interface IPasswordHasher
    {
        string HashPassword(string password);
    }
}