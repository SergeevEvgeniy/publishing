using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.ModelBinding;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

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

        [Route("journalists/{id}")]
        public async Task<IHttpActionResult> GetJournalistStatistics(int? id)
        {
            HttpContext.Current.Response.Headers.Add("Access-Control-Allow-Origin", "*");
            var result = await service.GetJournalistStatistics(id);
            if (!result.IsSuccessful)
            {
                return BadRequest(result.GetFailureMessage());
            }

            return Json(result.GetContent(),
                new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }

        [HttpPost]
        [Route("journalists")]
        public async Task<IHttpActionResult> FilterJournalists(JournalistListFilterDTO filter)
        {
            var result = await service.GetJournalistList(filter);
            if (!result.IsSuccessful)
            {
                return BadRequest(result.GetFailureMessage());
            }

            return Json(result.GetContent(),
                new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }
    }
}