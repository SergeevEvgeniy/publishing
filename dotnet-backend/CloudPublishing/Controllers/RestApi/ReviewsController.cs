using CloudPublishing.Business.Services.Interfaces;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace CloudPublishing.Controllers.RestApi
{
    public class ReviewsController : ApiController
    {
        private IReviewService reviewService;

        public ReviewsController(IReviewService reviewService)
        {
            this.reviewService = reviewService;
        }

        [HttpGet]
        public IHttpActionResult CheckArticleBeforePublication(int id)
        {
            var reviews = reviewService.GetUnapprovedReviewsByArticleId(id);
            if(reviews == null)
            {
                return StatusCode(HttpStatusCode.NotFound);
            }
            else if(reviews.Count() > 0)
            {
                return Ok(false);
            }
            return Ok(true);
        }
    }
}
