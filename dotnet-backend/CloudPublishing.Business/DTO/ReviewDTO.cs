namespace CloudPublishing.Business.DTO
{
    /// <summary>
    ///     Транспортная сущеность рецензии
    /// </summary>
    public class ReviewDTO
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
        ///     Возвращает или задает одобрение статьи редактором
        /// </summary>
        public bool Approved { get; set; }
    }
}