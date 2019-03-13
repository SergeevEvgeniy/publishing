using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingViewModel
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public IEnumerable<int> TopicsId { get; set; }
        public IEnumerable<int> EmployeesId { get; set; }

    }
}