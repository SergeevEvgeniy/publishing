using System.Collections.Generic;

namespace CloudPublishing.Data.Entities
{
    /// <summary>
    ///     Класс-сущность представляющий собой тип образования
    /// </summary>
    public class Education
    {
        /// <summary>
        ///     Создает новый экземпляр типа образования с пустим списком сотрудников с таким образованием
        /// </summary>
        public Education()
        {
            Employees = new HashSet<Employee>();
        }

        /// <summary>
        ///     Взвращает или устанавливает идентификатор типа образования
        /// </summary>
        public int Id { get; set; }

        /// <summary>
        ///     Взвращает или устанавливает наименование типа образования
        /// </summary>
        public string Title { get; set; }

        /// <summary>
        ///     Взвращает или устанавливает коллекцию сотрудников с таким образованием
        /// </summary>
        public virtual ICollection<Employee> Employees { get; set; }
    }
}