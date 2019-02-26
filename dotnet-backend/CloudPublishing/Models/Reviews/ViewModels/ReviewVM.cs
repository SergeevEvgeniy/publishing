using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class ReviewVM
    {
        public int ArticleId { get; set; }

        public int ReviewerId { get; set; }

        public string Content { get; set; }

        public bool Approved { get; set; }
    }
}