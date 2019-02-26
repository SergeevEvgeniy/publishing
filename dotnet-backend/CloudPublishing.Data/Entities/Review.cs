namespace CloudPublishing.Data.Entities
{
    public class Review
    {
        public int ArticleId { get; set; }
        
        public int ReviwerId { get; set; }
        
        public string Content { get; set; }
        
        public bool Approved { get; set; }
    }
}
