using Newtonsoft.Json;

namespace CloudPublishing.Business.DTO
{
    public class ArticleDTO
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        public int PublishingId { get; set; }

        public int TopicId { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("content")]
        public string Content { get; set; }

        public int AuthorId { get; set; }

        [JsonProperty("topic")]
        public TopicDTO Topic { get; set; }

        [JsonProperty("author")]
        public EmployeeDTO Employee { get; set; }

        [JsonProperty("publishing")]
        public PublishingDTO Publishing { get; set; }
    }
}