using System;
using System.Security.Cryptography;
using System.Security.Policy;
using System.Text;
using Microsoft.AspNet.Identity;

namespace CloudPublishing.Data.Identity
{
    public class CustomPasswordHasher : IPasswordHasher
    {
        private readonly string salt;

        public CustomPasswordHasher()
        {
            salt = "Strongest salt ever";
        }

        public string HashPassword(string password)
        {
            using (var provider = new MD5CryptoServiceProvider())
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

        public PasswordVerificationResult VerifyHashedPassword(string hashedPassword, string providedPassword)
        {
            var providedPasswordHash = HashPassword(providedPassword);
            return hashedPassword == providedPasswordHash
                ? PasswordVerificationResult.Success
                : PasswordVerificationResult.Failed;
        }
    }
}