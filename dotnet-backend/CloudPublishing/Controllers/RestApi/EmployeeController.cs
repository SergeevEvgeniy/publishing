using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.ModelBinding;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api/employee")]
    public class EmployeeController : ApiController
    {
        private readonly IEmployeeApiService service;

        public EmployeeController(IEmployeeApiService service)
        {
            this.service = service;
        }

        [HttpPost]
        public IHttpActionResult GetEmployeeInfo(List<int> idList)
        {
            var result = service.GetEmployeeInformation(idList);
            if (!result.IsSuccessful)
            {
                return BadRequest(result.GetFailureMessage());
            }
            // TODO: Настроить результирующий список сотрудников
            return Json(result.GetContent(),
                new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
        }
    }
}