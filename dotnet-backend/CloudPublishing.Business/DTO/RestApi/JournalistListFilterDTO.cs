namespace CloudPublishing.Business.DTO.RestApi
{
    public class JournalistListFilterDTO
    {
        public int? PublishingId { get; set; }

        public int? IssueId { get; set; }

        public int? TopicId { get; set; }

        public string ArticleTitle { get; set; }
    }
}