using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Models.Employees.ViewModels.Abstract;

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
        public async Task<ActionResult> List()
        {
            var list = await service.GetEmployeeList();

            return View(new MapperConfiguration(cfg => cfg.CreateMap<EmployeeDTO, EmployeeViewModel>()).CreateMapper()
                .Map<IEnumerable<EmployeeDTO>, List<EmployeeViewModel>>(list.ToList()).Select(x =>
                {
                    // TODO: Enum.TryParse
                    x.Type = Types[(EmployeeType) Enum.Parse(typeof(EmployeeType), x.Type)];
                    x.Sex = Sexes[(Sex) Enum.Parse(typeof(Sex), x.Sex)];
                    return x;
                }));
        }

        [HttpGet]
        public async Task<ActionResult> Edit(int? id)
        {
            if (id == null) return new HttpStatusCodeResult(HttpStatusCode.BadRequest, "Не указан идентификатор");
            var result = await service.GetEmployeeById(id.Value);
            var model = new MapperConfiguration(cfg => cfg.CreateMap<EmployeeDTO, EmployeeEditModel>())
                .CreateMapper().Map<EmployeeDTO, EmployeeEditModel>(result);
            model.TypeList = GetEmployeeTypeSelectList();
            model.EducationList = (await service.GetEducationList())
                .Select(x => new SelectListItem {Value = x.Id.ToString(), Text = x.Title}).ToList();
            return View(model);
        }

        [HttpGet]
        public async Task<ActionResult> Create()
        {
            var model = new EmployeeCreateModel
            {
                EducationList = (await service.GetEducationList())
                    .Select(x => new SelectListItem {Value = x.Id.ToString(), Text = x.Title}).ToList(),
                TypeList = GetEmployeeTypeSelectList()
            };
            return View(model);
        }

        [HttpPost]
        public async Task<ActionResult> Create(EmployeeModel employee)
        {
            if (!ModelState.IsValid)
            {
                var model = new MapperConfiguration(cfg => cfg.CreateMap<EmployeeModel, EmployeeCreateModel>())
                    .CreateMapper().Map<EmployeeModel, EmployeeCreateModel>(employee);
                model.EducationList = (await service.GetEducationList())
                    .Select(x => new SelectListItem {Value = x.Id.ToString(), Text = x.Title}).ToList();
                model.TypeList = GetEmployeeTypeSelectList();

                return View(model);
            }

            var result =
                await service.CreateEmployee(
                    new MapperConfiguration(cfg => cfg.CreateMap<EmployeeModel, EmployeeDTO>())
                        .CreateMapper().Map<EmployeeModel, EmployeeDTO>(employee));

            return RedirectToAction("List");
        }
    }
}