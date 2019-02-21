using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Identity.Managers;
using CloudPublishing.Models.Employees.Services.Interfaces;
using CloudPublishing.Models.Employees.Util;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Models.Shared.DTO;
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

        private EmployeeManager UserManager => HttpContext.GetOwinContext().GetUserManager<EmployeeManager>();

        private IAuthenticationManager AuthenticationManager => HttpContext.GetOwinContext().Authentication;

        private static List<SelectListItem> GetEmployeeTypeSelectList()
        {
            return new List<SelectListItem>
            {
                new SelectListItem {Value = EmployeeType.E.ToString(), Text = Types[EmployeeType.E]},
                new SelectListItem {Value = EmployeeType.J.ToString(), Text = Types[EmployeeType.J]}
            };
        }

        private async Task<List<SelectListItem>> GetEmployeeEducationList()
        {
            var list = await service.GetEducationList();
            return list.IsSuccessful
                ? list.GetContent().Select(x => new SelectListItem { Text = x.Title, Value = x.Id.ToString() })
                    .ToList()
                : new List<SelectListItem>();
        }

        [HttpGet]
        public async Task<ActionResult> List()
        {
            var result = await service.GetEmployeeList();
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
        public async Task<ActionResult> Login(LoginViewModel model)
        {
            if (!ModelState.IsValid) return View(model);

            var user = await UserManager.FindAsync(model.Email, model.Password);

            if (user == null)
            {
                ModelState.AddModelError("", "Неверный логин или пароль.");
                return View(model);
            }

            var claim = await UserManager.CreateIdentityAsync(user, DefaultAuthenticationTypes.ApplicationCookie);
            AuthenticationManager.SignOut();
            AuthenticationManager.SignIn(new AuthenticationProperties
            {
                IsPersistent = model.CheckOut
            }, claim);

            return RedirectToAction("List", "Employee");
        }

        [HttpGet]
        public async Task<ActionResult> Create()
        {
            var model = new EmployeeCreateModel
            {
                TypeList = GetEmployeeTypeSelectList(),
                EducationList = await GetEmployeeEducationList()
            };
            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();
            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(EmployeeCreateModel model)
        {
            if (!ModelState.IsValid) return View(model);

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            var result = await service.CreateEmployee(HttpContext.GetOwinContext(), user);

            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                return View(model);
            }

            TempData["Message"] = result.GetContent();

            return RedirectToAction("Create");
        }

        [HttpGet]
        public async Task<ActionResult> Edit(int? id)
        {
            if (id == null) return null;

            var result = await service.GetEmployeeById(id.Value);
            if (!result.IsSuccessful) return null;

            var model = mapper.Map<EmployeeDTO, EmployeeEditModel>(result.GetContent());
            model.TypeList = GetEmployeeTypeSelectList();
            model.EducationList = await GetEmployeeEducationList();

            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();

            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(EmployeeEditModel model)
        {
            model.TypeList = GetEmployeeTypeSelectList();
            model.EducationList = await GetEmployeeEducationList();

            if (!ModelState.IsValid) return View(model);
            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            var result = await service.EditEmployee(HttpContext.GetOwinContext(), user);
            TempData["Message"] = result.GetContent();

            return !result.IsSuccessful ? RedirectToAction("Edit", new {id = model.Id}) : RedirectToAction("List");
        }
    }
}