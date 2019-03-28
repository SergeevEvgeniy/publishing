using System;
using System.Collections.Generic;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;

namespace CloudPublishing.Business.Services.Interfaces
{
    /// <inheritdoc />
    /// <summary>
    ///     Предоставляет средства для работы с данными сотрудников
    /// </summary>
    public interface IEmployeeService : IDisposable
    {
        /// <summary>
        ///     Получить коллекцию сотрудников
        /// </summary>
        /// <returns>Коллекция сотрудников издательства или пустая коллекция, если сотрудники отсутствуют</returns>
        IEnumerable<EmployeeDTO> GetEmployees();

        /// <summary>
        ///     Получить коллекцию сотрудников определенного типа
        /// </summary>
        /// <param name="type">Тип сотрудника</param>
        /// <returns>Коллекция сотрудников определенного типа или пустая коллекция, если сотрудники отсутствуют</returns>
        IEnumerable<EmployeeDTO> GetEmployees(string type);

        /// <summary>
        ///     Получить коллекцию сотрудников с определенной фамилией по коллекции идентификаторов
        /// </summary>
        /// <param name="idList">Список идентификаторов сотрудников</param> 
        /// <param name="lastName">Строка, с которой начинается фамилия сотрудника или null, если фильтр по фамилии не нужен</param>
        /// <returns>Коллекция сотрудников издательства или пустая коллекция, если такие сотрудники не найдены</returns>
        IEnumerable<EmployeeDTO> GetEmployeesFromList(IEnumerable<int> idList, string lastName = null);

        /// <summary>
        ///     Получить коллекцию возможных типов образования сотрудников
        /// </summary>
        /// <returns>Коллекция возможных типов образования или пустая коллекция, если типов отсутствуют</returns>
        IEnumerable<EducationDTO> GetEducationList();

        /// <summary>
        ///     получить данные сотрудника
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <returns>Данные сотрудника или null, если сотрудник не найден</returns>
        EmployeeDTO GetEmployeeById(int id);

        /// <summary>
        ///     Получить статистику по статьям журналиста
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <returns>Данные о сотруднике и статистика по его статьям</returns>
        JournalistStatisticsDTO GetJournalistStatistics(int id);

        /// <summary>
        ///     Получить коллекцию типов сотрудника
        /// </summary>
        /// <returns>Коллекция типов сотрудника, или пустая коллекция, если типы отстутствуют</returns>
        IDictionary<string, string> GetEmployeeTypes();

        /// <summary>
        ///     Создать нового прользователя
        /// </summary>
        /// <param name="entity">Сущность нового сотрудника</param>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        void CreateEmployee(EmployeeDTO entity);

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
        void EditEmployee(EmployeeDTO entity);

        /// <summary>
        ///     Удалить сотрудника
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        /// <exception cref="EntityNotFoundException">Возникает если пользователь с таким идентификатором не найден</exception>
        /// <exception cref="ChiefEditorRoleChangeException">Возникает, если удаляемый пользователь является главным редактором</exception>
        void DeleteEmployee(int id);

        /// <summary>
        ///     Аутентифицировать сотрудника по его почте и паролю
        /// </summary>
        /// <param name="email">Почтовый адрес сотрудника</param>
        /// <param name="password">Пароль сотрудника</param>
        /// <returns>Сущность пользователя или null, если такого пользователя нет</returns>
        /// <exception cref="InvalidOperationException">Возникает при ошибке в работе с базой данных</exception>
        EmployeeDTO AuthenticateEmployee(string email, string password);
    }
}