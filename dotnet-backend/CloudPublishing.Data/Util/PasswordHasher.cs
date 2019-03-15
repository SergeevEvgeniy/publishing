using System.Security.Cryptography;
using System.Text;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Util
{
    public class PasswordHasher : IPasswordHasher
    {
        private readonly string salt;

        public PasswordHasher()
        {
            salt = "Strongest salt ever";
        }

        public string HashPassword(string password)
        {
            using(var provider = new MD5CryptoServiceProvider())
            {
                var bytes = provider.ComputeHash(Encoding.UTF8.GetBytes(password + salt));
                var sb = new StringBuilder();
                foreach (var b in bytes)
                {
                    sb.Append(b.ToString("x2"));
                }

                return sb.ToString();
            }
        }
    }
}