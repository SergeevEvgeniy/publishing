using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing.Controllers.RestApi
{
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
        public IHttpActionResult GetEmployeeData()
        {
            var result = service.GetEmployeeList();

            return Json(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(result),
                new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }

        [HttpGet]
        public IHttpActionResult GetEmployeeData(int? id)
        {
            if (id == null)
            {
                return ResponseMessage(new HttpResponseMessage((HttpStatusCode) 422));
            }

            var result = service.GetEmployeeById(id.Value);

            return Json(mapper.Map<EmployeeDTO, EmployeeData>(result),
                new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }

        [HttpPost]
        public IHttpActionResult GetEmployeeData(EmployeeFilter filter)
        {
            var result = service.GetEmployeeList(filter.IdList, filter.LastName);
            return Json(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(result),
                new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }
    }
}