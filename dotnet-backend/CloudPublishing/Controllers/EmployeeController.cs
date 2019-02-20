using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Models.Employees.DTO;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.Identity.Managers;
using CloudPublishing.Models.Employees.Services.Interfaces;
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

        private EmployeeUserManager UserManager => HttpContext.GetOwinContext().GetUserManager<EmployeeUserManager>();

        private IAuthenticationManager AuthenticationManager => HttpContext.GetOwinContext().Authentication;

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

        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Login(LoginViewModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

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
            var model = new E
            return View();
        }
    }
}