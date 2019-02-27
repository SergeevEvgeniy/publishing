namespace CloudPublishing.Models.Employees.RestApi
{
    public class JournalistListFilterModel
    {
        public int PublishingId { get; set; }

        public int IssueId { get; set; }

        public int TopicId { get; set; }

        public string ArticleTitle { get; set; }
    }
}