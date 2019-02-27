using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using AutoMapper;
using CloudPublishing.Business.DTO.RestApi;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.RestApi;
using CloudPublishing.Util;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api")]
    public class EmployeeController : ApiController
    {
        private readonly IEmployeeApiService service;
        private readonly IMapper mapper;

        public EmployeeController(IEmployeeApiService service)
        {
            this.service = service;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeApiMapProfile())).CreateMapper();
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