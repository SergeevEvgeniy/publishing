using System;
using System.Collections.Generic;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.Interfaces
{
    /// <summary>
    ///     Репозиторий для доступа к данным сотрудников издательства
    /// </summary>
    public interface IEmployeeRepository
    {
        /// <summary>
        ///     Получить список всех сотрудников
        /// </summary>
        /// <returns>Коллекция сотрудников или пустая коллекция, если сотрудников не найдено</returns>
        IEnumerable<Employee> GetAll();

        /// <summary>
        ///     Получить данные конкретного сотрудника
        /// </summary>
        /// <param name="id">Уникальный идентификатор сотрудника</param>
        /// <returns>Данные сотрудника или null, если сотрудник не найден</returns>
        Employee Get(int id);

        /// <summary>
        ///     Добавить нового сотрудника в базу данных
        /// </summary>
        /// <param name="item">Новый сотрудник</param>
        void Create(Employee item);

        /// <summary>
        ///     Обновить данные сотрудника
        /// </summary>
        /// <param name="item">Сущность с обновленными полями</param>
        void Update(Employee item);


        /// <summary>
        ///     Удалить существующего сотрудника
        /// </summary>
        /// <param name="id">Уникальный идентификатор сотрудника</param>
        void Delete(int id);

        /// <summary>
        ///     Отобрать сотрудников, подходящих под определенное условие
        /// </summary>
        /// <param name="predicate">Условие отбора</param>
        /// <returns>Коллекция сотрудников подходящих под условие или пустая коллекция, если не найдено таких сотрудников </returns>
        IEnumerable<Employee> Find(Func<Employee, bool> predicate);

        /// <summary>
        ///     Получить список типов образования сотрудника
        /// </summary>
        /// <returns>Коллекция типов образования или пустая коллекция, если типов не найдено</returns>
        IEnumerable<Education> GetEducationList();
    }
}