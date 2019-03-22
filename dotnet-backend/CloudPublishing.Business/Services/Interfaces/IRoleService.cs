using CloudPublishing.Business.Infrastructure;

namespace CloudPublishing.Business.Services.Interfaces
{
    /// <summary>
    ///     Предоставляет средства для работы с ролями сотрудников
    /// </summary>
    public interface IRoleService
    {
        /// <summary>
        ///     Проверить сотрудника на принадлежность к роли
        /// </summary>
        /// <param name="email">Почтовый адрес сотрудника</param>
        /// <param name="roleName">Наименование роли</param>
        /// <returns>true, если сотрудник принадлежит к роли, и false в противном случае</returns>
        /// <exception cref="EntityNotFoundException">Возникает если сотрудник не найден</exception>
        bool IsUserInRole(string email, string roleName);

        /// <summary>
        ///     Получить список ролей сотрудника
        /// </summary>
        /// <param name="email">Почтовый адрес сотрудника</param>
        /// <returns>Массив ролей пользователя</returns>
        /// <exception cref="EntityNotFoundException">Возникает если сотрудник не найден</exception>
        string[] GetRolesForUser(string email);
    }
}