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
            var result = service.GetEmployees().ToList();

            return !result.Any()
                ? (IHttpActionResult) ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent))
                : Ok(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeItemModel>>(result));
        }

        [HttpGet]
        [Route("employees/{id}")]
        public IHttpActionResult GetEmployeeData(int? id)
        {
            if (id == null)
            {
                return BadRequest();
            }

            var result = service.GetEmployeeById(id.Value);

            return result == null
                ? (IHttpActionResult) ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent))
                : Ok(mapper.Map<EmployeeDTO, EmployeeData>(result));
        }

        [HttpPost]
        [Route("employees")]
        public IHttpActionResult GetEmployeeData(EmployeeFilter filter)
        {
            var list = service.GetEmployeesFromList(filter.IdList, filter.LastName).ToList();
            return !list.Any()
                ? (IHttpActionResult) ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent))
                : Ok(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(list));
        }

        [HttpGet]
        [Route("journalists/{id}")]
        [HttpRequestException]
        public IHttpActionResult GetEmployeeStatistics(int? id)
        {
            return id == null ? (IHttpActionResult) BadRequest() : Ok(service.GetJournalistStatistics(id.Value));
        }
    }
}