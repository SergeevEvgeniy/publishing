using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Controllers
{
    public class EmployeeController : Controller
    {
        private static readonly IDictionary<EmployeeType, string> Types;
        private static readonly IDictionary<Sex, string> Sexes;

        private readonly IEmployeeService service;

        static EmployeeController()
        {
            Types = new Dictionary<EmployeeType, string>
            {
                {EmployeeType.E, "Редактор"},
                {EmployeeType.J, "Журналист"}
            };
            Sexes = new Dictionary<Sex, string>
            {
                {Sex.M, "М"},
                {Sex.F, "Ж"}
            };
        }

        public EmployeeController(IEmployeeService service)
        {
            this.service = service;
        }

        private static List<SelectListItem> GetEmployeeTypeSelectList()
        {
            return new List<SelectListItem>
            {
                new SelectListItem {Value = EmployeeType.E.ToString(), Text = Types[EmployeeType.E]},
                new SelectListItem {Value = EmployeeType.J.ToString(), Text = Types[EmployeeType.J]}
            };
        }

        [HttpGet]
        [Authorize]
        public async Task<ActionResult> List()
        {
            var result = await service.GetEmployeeList();
            if (!result.IsSuccessful) return null;

            return View(new MapperConfiguration(cfg => cfg.CreateMap<EmployeeDTO, EmployeeViewModel>()).CreateMapper()
                .Map<IEnumerable<EmployeeDTO>, List<EmployeeViewModel>>(result.GetContent().ToList()).Select(x =>
                {
                    // TODO: Enum.TryParse
                    x.Type = Types[(EmployeeType) Enum.Parse(typeof(EmployeeType), x.Type)];
                    x.Sex = Sexes[(Sex) Enum.Parse(typeof(Sex), x.Sex)];
                    return x;
                }));
        }
    }
}