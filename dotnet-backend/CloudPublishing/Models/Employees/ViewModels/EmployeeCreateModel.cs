using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;
using CloudPublishing.Models.Employees.ViewModels.Abstract;

namespace CloudPublishing.Models.Employees.ViewModels
{
    public class EmployeeCreateModel : EmployeeModel
    {
        [Required]
        [StringLength(255)]
        [DisplayName("Пароль")]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [Required]
        [StringLength(255)]
        [System.ComponentModel.DataAnnotations.Compare("Password")]
        [DisplayName("Пароль повторно")]
        [DataType(DataType.Password)]
        public string PasswordAgain { get; set; }

        public IReadOnlyList<SelectListItem> EducationList { get; set; }

        public IReadOnlyList<SelectListItem> TypeList { get; set; }
    }
}