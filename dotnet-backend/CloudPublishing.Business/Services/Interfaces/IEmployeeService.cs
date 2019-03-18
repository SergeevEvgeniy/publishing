using System;
using System.Collections.Generic;
using CloudPublishing.Business.DTO;

namespace CloudPublishing.Business.Services.Interfaces
{
    /// <inheritdoc />
    /// <summary>
    ///     Предоставляет средства для работы с данными пользователей
    /// </summary>
    public interface IEmployeeService : IDisposable
    {
        /// <summary>
        ///     Получить коллекцию сотрудников, работающих в издательстве
        /// </summary>
        /// <returns>Коллекция сотрудников издательства или пустая коллекция, если сотрудники отсутствуют</returns>
        IEnumerable<EmployeeDTO> GetEmployeeList();

        /// <summary>
        ///     Получить коллекцию сотрудников с определенной фамилией по коллекции идентификаторов
        /// </summary>
        /// <param name="idList">Список идентификаторов сотрудников</param>
        /// <param name="lastName">Строка, с которой начинается фамилия сотрудника</param>
        /// <returns>Коллекция сотрудников издательства или пустая коллекция, если сотрудники отсутствуютК</returns>
        IEnumerable<EmployeeDTO> GetEmployeeList(IEnumerable<int> idList, string lastName);

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
        ///     Получить коллекцию типов сотрудника
        /// </summary>
        /// <returns>Коллекция типов сотрудника, или пустая коллекция, если типы отстутствуют</returns>
        IDictionary<string, string> GetEmployeeTypes();
    }
}