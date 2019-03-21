using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Employees.ViewModels
{
    public class LoginViewModel
    {
        [Required(ErrorMessage = "Для входа требуется почтовый адрес")]
        [StringLength(255, ErrorMessage = "Слишком длинный почтовый адрес")]
        [DataType(DataType.EmailAddress)]
        [DisplayName("E-mail")]
        public string Email { get; set; }

        [Required(ErrorMessage = "Для входа необходимо ввести пароль")]
        [StringLength(255, ErrorMessage = "Слишком длинный пароль")]
        [DataType(DataType.Password)]
        [DisplayName("Пароль")]
        public string Password { get; set; }

        [DisplayName("Запомнить меня")]
        public bool CheckOut { get; set; }
    }
}