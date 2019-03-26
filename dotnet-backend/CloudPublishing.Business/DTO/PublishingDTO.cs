using System.Collections.Generic;

namespace CloudPublishing.Business.DTO
{
    public class PublishingDTO
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public IEnumerable<TopicDTO> Topics { get; set; }
        public IEnumerable<EmployeeDTO> Editors { get; set; }
        public IEnumerable<EmployeeDTO> Journalists { get; set; }
    }
}