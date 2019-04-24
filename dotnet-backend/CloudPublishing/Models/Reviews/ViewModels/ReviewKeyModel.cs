using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class ReviewKeyModel
    {
        [Required]
        public int ArticleId { get; set; }

        [Required]
        public int ReviwerId { get; set; }
    }
}