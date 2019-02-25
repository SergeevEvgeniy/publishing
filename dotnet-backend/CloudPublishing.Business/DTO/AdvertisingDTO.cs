namespace CloudPublishing.Business.DTO
{
    public class AdvertisingDTO
    {
        public int Id { get; set; }

        public int IssueId { get; set; }

        public string FilePath { get; set; }

        public IssueDTO Issue { get; set; }
    }
}