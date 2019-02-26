using System;

namespace CloudPublishing.Business.DTO
{
    public class MailingResultDTO
    {
        public int MailingId { get; set; }

        public int IssueId { get; set; }

        public DateTime Date { get; set; }

        public string Result { get; set; }

        public IssueDTO Issue { get; set; }

        public MailingDTO Mailing { get; set; }
    }
}