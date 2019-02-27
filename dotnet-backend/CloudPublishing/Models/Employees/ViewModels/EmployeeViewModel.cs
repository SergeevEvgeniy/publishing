using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;

namespace CloudPublishing.Models.Employees.ViewModels
{
    public class EmployeeViewModel
    {
        public int Id { get; set; }

        [DisplayName("Имя")]
        public string FirstName { get; set; }

        [DisplayName("Фимилия")]
        public string LastName { get; set; }

        [DisplayName("Пол")]
        public string Sex { get; set; }

        [DisplayName("Год рождения")]
        public string BirthYear { get; set; }

        [DisplayName("Тип сотрудника")]
        public string Type { get; set; }
    }
}