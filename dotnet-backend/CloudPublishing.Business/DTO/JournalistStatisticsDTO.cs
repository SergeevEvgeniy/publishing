using System.Collections.Generic;

namespace CloudPublishing.Business.DTO
{
    /// <summary>
    ///     Класс для представления статистики журналиста
    /// </summary>
    public class JournalistStatisticsDTO
    {
        /// <summary>
        ///     Возвращает или задает идентификатор журналиста
        /// </summary>
        public int Id { get; set; }

        /// <summary>
        ///     Возвращиет или задает имя журналиста
        /// </summary>
        public string FirstName { get; set; }

        /// <summary>
        ///     Возвращиет или задает фамилию журналиста
        /// </summary>
        public string LastName { get; set; }

        /// <summary>
        ///     Возвращиет или задает отчество журналиста
        /// </summary>
        public string MiddleName { get; set; }

        /// <summary>
        ///     Возвращиет или задает почтновый адрес журналиста
        /// </summary>
        public string Email { get; set; }

        /// <summary>
        ///     Возвращиет или задает пол сотрудника
        /// </summary>
        public string Sex { get; set; }

        /// <summary>
        ///     Возвращиет или задает год рождения сотрудника
        /// </summary>
        public short BirthYear { get; set; }

        /// <summary>
        ///     Возвращиет или задает общее количество статей журналиста
        /// </summary>
        public int ArticleCount { get; set; }

        /// <summary>
        ///     Возвращиет или задает количество статей журналиста по изданиям
        /// </summary>
        public IDictionary<string, int> ArticleCountByPublishing { get; set; }

        /// <summary>
        ///     Возвращиет или задает количество статей журналиста по рубрикам
        /// </summary>
        public IDictionary<string, int> ArticleCountByTopics { get; set; }
    }
}