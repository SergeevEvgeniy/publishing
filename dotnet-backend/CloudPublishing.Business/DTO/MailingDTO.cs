namespace CloudPublishing.Business.DTO
{
    public class MailingDTO
    {
        public int Id { get; set; }

        public int PublishingId { get; set; }

        public PublishingDTO Publishing { get; set; }
    }
}