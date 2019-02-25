using System;

namespace CloudPublishing.Business.DTO
{
    public class IssueDTO
    {
        public int Id { get; set; }

        public int PublishingId { get; set; }

        public string Number { get; set; }

        public DateTime Date { get; set; }

        public bool Published { get; set; }

        public PublishingDTO Publishing { get; set; }
    }
}