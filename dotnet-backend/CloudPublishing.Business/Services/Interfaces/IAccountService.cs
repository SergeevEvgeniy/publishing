using System;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;

namespace CloudPublishing.Business.Services.Interfaces
{
    /// <summary>
    ///     Предоставляет средства для работы с данными сотрудников, необходимыми для аутентификации
    /// </summary>
    public interface IAccountService
    {
        /// <summary>
        ///     Создать нового прользователя
        /// </summary>
        /// <param name="entity">Сущность нового сотрудника</param>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        void CreateAccount(EmployeeDTO entity);

        /// <summary>
        ///     Изменить данные сотрудника
        /// </summary>
        /// <param name="entity">Обновленная сущность сотрудника</param>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        /// <exception cref="EntityNotFoundException">Возникает если пользователь с таким идентификатором не найден</exception>
        /// <exception cref="ChiefEditorRoleChangeException">
        ///     Возникает, если обновляемый польватель является главным редактором, а
        ///     в процессе обновления он исключается из данной роли
        /// </exception>
        void EditAccount(EmployeeDTO entity);

        /// <summary>
        ///     Удалить сотрудника
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        /// <exception cref="EntityNotFoundException">Возникает если пользователь с таким идентификатором не найден</exception>
        /// <exception cref="ChiefEditorRoleChangeException">Возникает, если удаляемый пользователь является главным редактором</exception>
        void DeleteAccount(int id);

        /// <summary>
        ///     Аутентифицировать сотрудника по его почте и паролю
        /// </summary>
        /// <param name="email">Почтовый адрес сотрудника</param>
        /// <param name="password">Пароль сотрудника</param>
        /// <returns>Сущность пользователя или null, если такого пользователя нет</returns>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        EmployeeDTO AuthenticateUser(string email, string password);
    }
}