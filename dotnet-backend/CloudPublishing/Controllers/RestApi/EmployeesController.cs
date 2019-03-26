using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ApiModels;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing.Controllers.RestApi
{
    //[RoutePrefix("api")]
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
        //[Route("employees")]
        public IHttpActionResult GetEmployeeData()
        {
            var result = service.GetEmployees();

            return Ok(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(result));
        }

        [HttpGet]
        //[Route("employees/{id}")]
        public IHttpActionResult GetEmployeeData(int? id)
        {
            if (id == null)
            {
                return ResponseMessage(new HttpResponseMessage((HttpStatusCode) 422));
            }

            var result = service.GetEmployeeById(id.Value);

            return Ok(mapper.Map<EmployeeDTO, EmployeeData>(result));
        }

        [HttpPost]
        //[Route("employees")]
        public IHttpActionResult GetEmployeeData(EmployeeFilter filter)
        {
            return Ok(
                mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(
                    service.GetEmployeesFromList(filter.IdList, filter.LastName, filter.Type)));
        }
    }
}