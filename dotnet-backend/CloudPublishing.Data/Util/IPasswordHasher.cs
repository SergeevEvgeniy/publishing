namespace CloudPublishing.Data.Util
{
    /// <summary>
    /// Представляет собой модуль, отвечающий за хэширование паролей
    /// </summary>
    public interface IPasswordHasher
    {
        /// <summary>
        /// Метод для хэширования пароля
        /// </summary>
        /// <param name="password">Строка для хэширования</param>
        /// <returns>Хэш передаваемой строки</returns>
        string HashPassword(string password);
    }
}