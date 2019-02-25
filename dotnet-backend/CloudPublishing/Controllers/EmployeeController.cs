using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Util;

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
            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();
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
            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(EmployeeCreateModel model)
        {
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            var result = service.CreateEmployee(user);

            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            TempData["Message"] = "Пользователь успешно создан. Количество измененных пользователей: " +
                                  result.GetContent();


            return RedirectToAction("List");
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
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }
            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            var result = service.EditEmployee(user);
            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            TempData["Message"] = "Данные пользователя успешно обновлены. Количество измененных пользователей: " +
                                  result.GetContent();

            return RedirectToAction("List");
        }

        [AjaxOnly]
        public ActionResult Delete(int? id)
        {
            var result = service.DeleteEmployee(id);

            return Json(new
            {
                isSuccessful = result.IsSuccessful,
                message = result.IsSuccessful ? "Сотрудник успешно удален" : result.GetFailureMessage()
            }, JsonRequestBehavior.AllowGet);
        }
    }
}