namespace CloudPublishing.Business.DTO
{
    /// <summary>
    ///     Представляет транспортную сущность сотрудника для обмена между слоями
    /// </summary>
    public class EmployeeDTO
    {
        /// <summary>
        ///     Возвращает или устанавливает идентификатор сотрудника
        /// </summary>
        public int Id { get; set; }

        /// <summary>
        ///     Возвращиет или задает имя сотрудника
        /// </summary>
        public string FirstName { get; set; }

        /// <summary>
        ///     Возвращиет или задает фамилию сотрудника
        /// </summary>
        public string LastName { get; set; }

        /// <summary>
        ///     Возвращиет или задает отчество сотрудника
        /// </summary>
        public string MiddleName { get; set; }

        /// <summary>
        ///     Возвращиет или задает почтновый адрес сотрудника
        /// </summary>
        public string Email { get; set; }

        /// <summary>
        ///     Возвращиет или задает хэш пароля сотрудника
        /// </summary>
        public string Password { get; set; }

        /// <summary>
        ///     Возвращиет или задает пол сотрудника
        /// </summary>
        public SexDTO Sex { get; set; }

        /// <summary>
        ///     Возвращиет или задает год рождения сотрудника
        /// </summary>
        public short BirthYear { get; set; }

        /// <summary>
        ///     Возвращиет или задает адрес сотрудника
        /// </summary>
        public string Address { get; set; }

        /// <summary>
        ///     Возвращиет или задает тип сотрудника
        /// </summary>
        public TypeDTO Type { get; set; }

        /// <summary>
        ///     Возвращиет или задает идентификатор типа образования сотрудника
        /// </summary>
        public int? EducationId { get; set; }

        /// <summary>
        ///     Возвращиет или задает флаг, является ли сотрудник главным редактором
        /// </summary>
        public bool ChiefEditor { get; set; }

        /// <summary>
        ///     Возвращиет или задает тип образования сотрудника
        /// </summary>
        public EducationDTO Education { get; set; }
    }
}