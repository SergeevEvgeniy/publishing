using System.Web;
using System.Web.Http;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api")]
    public class EmployeeController : ApiController
    {
        [Route("journalists/{id:int}/statistics")]
        public IHttpActionResult GetEmployeeStatistics(int? id)
        {
            HttpContext.Current.Response.Headers.Add("Access-Control-Allow-Origin", "*");
            return Ok("u know why u can't see content? Cuz it's nothing");
        }
    }
}