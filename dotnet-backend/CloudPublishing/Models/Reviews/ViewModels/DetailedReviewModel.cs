namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class DetailedReviewModel
    {
        public string Publishing { get; set; }

        public string Topic { get; set; }

        public string Author { get; set; }

        public string Article { get; set; }

        public int ArticleId { get; set; }

        public bool Approved { get; set; }
    }
}