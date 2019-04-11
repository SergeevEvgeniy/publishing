using Newtonsoft.Json;

namespace CloudPublishing.Business.DTO
{
    public class ArticleDTO
    {
        public int Id { get; set; }

        public int PublishingId { get; set; }

        public int TopicId { get; set; }
        
        public string Title { get; set; }
        
        public string Content { get; set; }

        public int AuthorId { get; set; }
        
        public TopicDTO Topic { get; set; }
        
        public EmployeeDTO Employee { get; set; }
        
        public PublishingDTO Publishing { get; set; }
    }
}