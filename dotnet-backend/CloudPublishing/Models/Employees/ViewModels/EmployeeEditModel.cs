using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;

namespace CloudPublishing.Models.Employees.ViewModels
{
    public class EmployeeEditModel
    {
        [Key] public int Id { get; set; }

        [Required(ErrorMessage = "Имя обязательно к заполнению")]
        [StringLength(255)]
        [DisplayName("Имя")]
        public string FirstName { get; set; }

        [Required(ErrorMessage = "Фамилия обязательно к заполнению")]
        [StringLength(255)]
        [DisplayName("Фамилия")]
        public string LastName { get; set; }

        [StringLength(255)]
        [DisplayName("Отчество")]
        public string MiddleName { get; set; }

        [Required(ErrorMessage = "Необходимо указать адрес почтового ящика")]
        [StringLength(255)]
        [EmailAddress]
        [DisplayName("E-mail")]
        public string Email { get; set; }

        [StringLength(255)]
        [DataType(DataType.Password)]
        [DisplayName("Пароль")]
        public string Password { get; set; }

        [StringLength(255)]
        [DataType(DataType.Password)]
        [System.ComponentModel.DataAnnotations.Compare("Password", ErrorMessage = "Пароли должны совпадать")]
        [DisplayName("Пароль повторно")]
        public string ConfirmPassword { get; set; }

        [StringLength(1)] [DisplayName("Пол")] public string Sex { get; set; }

        [DisplayName("Год рождения")] public short BirthYear { get; set; }

        [Required(ErrorMessage = "Необходимо указать адрес")]
        [StringLength(255)]
        [DisplayName("Адрес")]
        public string Address { get; set; }

        [Required]
        [StringLength(1)]
        [DisplayName("Тип сотрудника")]
        public string Type { get; set; }

        [DisplayName("Образование")] public int? EducationId { get; set; }

        [DisplayName("Главный редактор")] public bool ChiefEditor { get; set; }

        public List<SelectListItem> TypeList { get; set; }

        public List<SelectListItem> EducationList { get; set; }
    }
}