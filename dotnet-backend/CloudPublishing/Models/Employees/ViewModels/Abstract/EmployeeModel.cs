using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Employees.ViewModels.Abstract
{
    public abstract class EmployeeModel
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [StringLength(255)]
        [DisplayName("Имя")]
        public string FirstName { get; set; }

        [Required]
        [StringLength(255)]
        [DisplayName("Фамилия")]
        public string LastName { get; set; }

        [StringLength(255)]
        [DisplayName("Отчество")]
        public string MiddleName { get; set; }

        [Required]
        [StringLength(255)]
        [DisplayName("E-mail")]
        [DataType(DataType.EmailAddress)]
        public string Email { get; set; }

        [Required]
        [StringLength(1)]
        [DisplayName("Пол")]
        public string Sex { get; set; }

        [DisplayName("Год рождения")]
        [StringLength(4)]
        public string BirthYear { get; set; }

        [Required]
        [StringLength(255)]
        [DisplayName("Адрес")]
        public string Address { get; set; }

        [Required]
        [StringLength(1)]
        [DisplayName("Тип сотрудника")]
        public string Type { get; set; }

        [DisplayName("Оюразование")]
        public int? EducationId { get; set; }

        [DisplayName("Главный редактор")]
        public bool ChiefEditor { get; set; }
    }
}