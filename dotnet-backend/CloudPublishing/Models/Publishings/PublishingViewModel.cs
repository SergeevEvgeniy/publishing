using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingViewModel
    {
        public int Id { get; set; }

        [Required (ErrorMessage = "Не указано название")]
        [StringLength(255, ErrorMessage = "Максимальная длина названи 255 символов")]
        public string Title { get; set; }

        [Required (ErrorMessage = "Не указан тип издания")]
        [RegularExpression(@"0|1", ErrorMessage = "Некорректный тип ")]
        public string Type { get; set; }

        [Required (ErrorMessage = "Не указано описание")]
        [StringLength(255, ErrorMessage = "Максимальная длина описания 255 символов")]
        public string Subjects { get; set; }

        public IEnumerable<int> TopicsId { get; set; }
        public IEnumerable<int> EmployeesId { get; set; }

    }
}