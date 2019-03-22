namespace CloudPublishing.Business.DTO
{
    /// <summary>
    ///     Представляет транспортную сущность типа образования сотрудника для обмена между слоями
    /// </summary>
    public class EducationDTO
    {
        /// <summary>
        ///     Взвращает или устанавливает идентификатор типа образования
        /// </summary>
        public int Id { get; set; }

        /// <summary>
        ///     Взвращает или устанавливает наименование типа образования
        /// </summary>
        public string Title { get; set; }
    }
}