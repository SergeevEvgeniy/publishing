namespace CloudPublishing.Business.DTO
{
    public class ReviewDTO
    {
        public int ArticleId { get; set; }

        public int ReviwerId { get; set; }

        public string Content { get; set; }

        public bool Approved { get; set; }
    }
}