using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api")]
    public class EmployeeController : ApiController
    {
        private readonly IEmployeeApiService service;

        public EmployeeController(IEmployeeApiService service)
        {
            this.service = service;
        }

        [Route("journalists/{id}/statistics")]
        public async Task<IHttpActionResult> GetJournalistStatistics(int? id)
        {
            HttpContext.Current.Response.Headers.Add("Access-Control-Allow-Origin", "*");
            return Json(await service.GetJournalistStatistics(id));
        }

        [HttpPost]
        [Route("journalists")]
        public async Task<IHttpActionResult> GetJournalistList(JournalistListFilterDTO filter)
        {
            HttpContext.Current.Response.Headers.Add("Access-Control-Allow-Origin", "*");
            return Json(await service.GetJournalistList(filter));
        }
    }
}