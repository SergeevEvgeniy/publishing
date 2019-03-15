using System.ComponentModel.DataAnnotations;

namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class ReviewModel
    {
        [Required]
        public int ArticleId { get; set; }

        [Required]
        public int ReviwerId { get; set; }

        [Required]
        public string Content { get; set; }

        [Required]
        public bool Approved { get; set; }
    }
}