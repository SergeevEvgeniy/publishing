using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Employees.ViewModels
{
    public class LoginViewModel
    {
        [Required]
        [StringLength(255)]
        [DataType(DataType.EmailAddress)]
        [DisplayName("E-mail")]
        public string Email { get; set; }

        [Required]
        [StringLength(255)]
        [DataType(DataType.Password)]
        [DisplayName("Пароль")]
        public string Password { get; set; }

        //[DisplayName("Запомнить меня")]
        //public bool CheckOut { get; set; }
    }
}