using System;

namespace CloudPublishing.Business.Infrastructure
{
    /// <inheritdoc />
    /// <summary>
    ///     Класс исключения для обозначения ошибок отсутствия сущности в базе данных
    /// </summary>
    public class EntityNotFoundException : ApplicationException
    {
        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр класса исключения используя конструктор базового класса, передавая ему сообщение об ошибке
        /// </summary>
        /// <param name="message">Сообщение об ошибке</param>
        public EntityNotFoundException(string message) : base(message)
        {
        }
    }
}