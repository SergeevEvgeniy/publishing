using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ApiModels;
using CloudPublishing.Util.Attributes;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api")]
    public class EmployeesController : ApiController
    {
        private readonly IMapper mapper;
        private readonly IEmployeeService service;

        public EmployeesController(IEmployeeService service, IMapper mapper)
        {
            this.service = service;
            this.mapper = mapper;
        }

        [HttpGet]
        [Route("employees")]
        public IHttpActionResult GetEmployeeData()
        {
            return ProduceHttpResult<EmployeeData>(service.GetEmployees());
        }

        [HttpGet]
        [Route("employees/{id:int}")]
        public IHttpActionResult GetEmployeeData(int id)
        {
            return ProduceHttpResult<EmployeeData>(service.GetEmployeeById(id));
        }

        [HttpPost]
        [Route("employees")]
        public IHttpActionResult GetEmployeeData(EmployeeFilter filter)
        {
            return ProduceHttpResult<EmployeeData>(service.GetEmployeesFromList(filter.IdList, filter.LastName));
        }

        [HttpGet]
        [Route("journalists/{id:int}")]
        [HttpRequestException]
        public IHttpActionResult GetJournalistStatistics(int id)
        {
            return Ok(service.GetJournalistStatistics(id));
        }

        private IHttpActionResult ProduceHttpResult<T>(IEnumerable<EmployeeDTO> collection)
        {
            return collection.Any()
                ? Ok(mapper.Map<IEnumerable<EmployeeDTO>, List<T>>(collection))
                : (IHttpActionResult)ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent));
        }

        private IHttpActionResult ProduceHttpResult<T>(EmployeeDTO employee)
        {
            return employee != null
                ? Ok(mapper.Map<EmployeeDTO, T>(employee))
                : (IHttpActionResult)ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent));
        }
    }
}