using System;

namespace CloudPublishing.Business.Infrastructure
{
    /// <inheritdoc />
    /// <summary>
    ///     Класс исключения для обозначения ошибок при операциях изменения и удаления главного редактора
    /// </summary>
    public class ChiefEditorRoleChangeException : ApplicationException
    {
        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр класса исключения используя конструктор базвого класса и задает сообщение о необходимости
        ///     передачи роли главного редактора
        /// </summary>
        public ChiefEditorRoleChangeException() : base("Сначала необходимо указать другого главного редактора")
        {
        }
    }
}