using System.Collections.Generic;

namespace CloudPublishing.Models.Reviews.ViewModels
{
    public class CreateReviewModel
    {
        public IEnumerable<PublishingModel> PublishingList { get; set; }        
    }
}