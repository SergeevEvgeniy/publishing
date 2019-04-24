namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class DeleteReviewModel
    {
        public bool IsPublished { get; set; }
        
        public int ArticleId { get; set; }

        public int ReviwerId { get; set; }
    }
}