using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees;
using CloudPublishing.Util;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using System.Collections.Generic;
using System.Web.Http;

namespace CloudPublishing.Controllers.RestApi
{
    public class EmployeesController : ApiController
    {
        private readonly IEmployeeService service;
        private readonly IMapper mapper;

        public EmployeesController(IEmployeeService service)
        {
            this.service = service;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper();
        }

        [HttpGet]
        public IHttpActionResult GetEmployeeData()
        {
            var result = service.GetEmployeeList();

            return Json(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(result), new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
        }

        [HttpGet]
        public IHttpActionResult GetEmployeeData(int? id)
        {
            var result = service.GetEmployeeById(id);

            return Json(mapper.Map<EmployeeDTO, EmployeeData>(result), new JsonSerializerSettings{ContractResolver = new CamelCasePropertyNamesContractResolver()});
        }

        [HttpPost]
        public IHttpActionResult GetEmployeeData(EmployeeFilter filter)
        {
            var result = service.GetEmployeeList(filter.IdList, filter.LastName);
            return Json(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeData>>(result), new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
        }
    }
}