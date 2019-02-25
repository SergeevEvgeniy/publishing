namespace CloudPublishing.Business.DTO
{
    public class MailingSubscriberDTO
    {
        public int MailingId { get; set; }

        public string Email { get; set; }

        public MailingDTO Mailing { get; set; }
    }
}