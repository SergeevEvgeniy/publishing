using System.Security.Cryptography;
using System.Text;

namespace CloudPublishing.Data.Util
{
    /// <inheritdoc />
    public class PasswordHasher : IPasswordHasher
    {
        private readonly string leftSalt;
        private readonly string rightSalt;

        /// <summary>
        ///     Создает экземпляр класса с предопределенной солью
        /// </summary>
        public PasswordHasher()
        {
            leftSalt = "cloud";
            rightSalt = "publishing";
        }

        /// <inheritdoc />
        public string HashPassword(string password)
        {
            using (var provider = new MD5CryptoServiceProvider())
            {
                var bytes = provider.ComputeHash(Encoding.UTF8.GetBytes(password));
                var sb = new StringBuilder();
                foreach (var b in bytes)
                {
                    sb.Append(b.ToString("x2"));
                }

                var hashedPassword = sb.ToString().ToUpper();
                bytes = provider.ComputeHash(Encoding.UTF8.GetBytes(leftSalt + hashedPassword + rightSalt));

                sb.Clear();

                foreach (var b in bytes)
                {
                    sb.Append(b.ToString("x2"));
                }

                return sb.ToString().ToUpper();
            }
        }
    }
}