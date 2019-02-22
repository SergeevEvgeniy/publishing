using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Util;
using CloudPublishing.Models.Employees.ViewModels;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;

namespace CloudPublishing.Controllers
{
    public class EmployeeController : Controller
    {
        private static readonly IDictionary<EmployeeType, string> Types;
        private static readonly IDictionary<Sex, string> Sexes;
        private readonly IMapper mapper;

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
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper();
        }

        private static List<SelectListItem> GetEmployeeTypeSelectList()
        {
            return new List<SelectListItem>
            {
                new SelectListItem {Value = EmployeeType.E.ToString(), Text = Types[EmployeeType.E]},
                new SelectListItem {Value = EmployeeType.J.ToString(), Text = Types[EmployeeType.J]}
            };
        }

        private List<SelectListItem> GetEmployeeEducationList()
        {
            var list = service.GetEducationList();
            return list.IsSuccessful
                ? list.GetContent().Select(x => new SelectListItem {Text = x.Title, Value = x.Id.ToString()})
                    .ToList()
                : new List<SelectListItem>();
        }

        [HttpGet]
        public ActionResult List()
        {
            var result = service.GetEmployeeList();
            if (!result.IsSuccessful) return null;

            return View(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeViewModel>>(result.GetContent().ToList())
                .Select(x =>
                {
                    x.Type = Types[(EmployeeType) Enum.Parse(typeof(EmployeeType), x.Type)];
                    x.Sex = Sexes[(Sex) Enum.Parse(typeof(Sex), x.Sex)];
                    return x;
                }));
        }

        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(LoginViewModel model)
        {
            if (!ModelState.IsValid) return View(model);

            return RedirectToAction("List", "Employee");
        }

        [HttpGet]
        public ActionResult Create()
        {
            var model = new EmployeeCreateModel
            {
                TypeList = GetEmployeeTypeSelectList(),
                EducationList = GetEmployeeEducationList()
            };
            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();
            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(EmployeeCreateModel model)
        {
            if (!ModelState.IsValid) return View(model);

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            var result = service.CreateEmployee(user);

            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                return View(model);
            }

            TempData["Message"] = result.GetContent();

            return RedirectToAction("Create");
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id == null) return null;

            var result = service.GetEmployeeById(id.Value);
            if (!result.IsSuccessful) return null;

            var model = mapper.Map<EmployeeDTO, EmployeeEditModel>(result.GetContent());
            model.TypeList = GetEmployeeTypeSelectList();
            model.EducationList = GetEmployeeEducationList();

            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();

            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(EmployeeEditModel model)
        {
            model.TypeList = GetEmployeeTypeSelectList();
            model.EducationList = GetEmployeeEducationList();

            if (!ModelState.IsValid) return View(model);
            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            var result = service.EditEmployee(user);
            TempData["Message"] = result.GetContent();

            return !result.IsSuccessful ? RedirectToAction("Edit", new {id = model.Id}) : RedirectToAction("List");
        }
    }
}