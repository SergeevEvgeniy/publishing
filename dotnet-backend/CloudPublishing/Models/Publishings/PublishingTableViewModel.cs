using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings.Enums;
using System;
using System.Collections.Generic;
using System.Linq;

namespace CloudPublishing.Models.Publishings
{
    public class PublishingTableViewModel
    {
        private static Dictionary<string, string> PublishingTyp = new Dictionary<string, string> { { "m", "Журнал" }, { "n", "Газета" } };
        public int Id { get; set; }
        public string Title { get; set; }
        public string Type { get; set; }
        public string Subjects { get; set; }
        public string Topics { get; set; }
    }
}