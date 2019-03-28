namespace CloudPublishing.Data.Entities
{
    /// <summary>
    ///     Сущность рецензии
    /// </summary>
    public class Review
    {
        /// <summary>
        ///     Возвращает или задает Id статьи
        /// </summary>
        public int ArticleId { get; set; }
        
        /// <summary>
        ///     Возвращает или задает Id редактора
        /// </summary>
        public int ReviwerId { get; set; }
        
        /// <summary>
        ///     Возвращает или задает содержимое статьи
        /// </summary>
        public string Content { get; set; }

        /// <summary>
        ///     Возвращает или задает флаг одобрения статьи редактором
        /// </summary>
        public bool Approved { get; set; }

        /// <summary>
        ///     Возвращает или задает редактора
        /// </summary>
        public Employee Employee { get; set; }
    }
}
