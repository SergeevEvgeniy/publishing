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
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Util;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;

namespace CloudPublishing.Controllers
{
    public class EmployeeController : Controller
    {
        private readonly IEmployeeService service;
        private readonly IAccountService accounts;
        private readonly IAuthenticationManager authenticationManager;

        private static readonly IDictionary<EmployeeType, string> Types;
        private static readonly IDictionary<Sex, string> Sexes;
        private readonly IMapper mapper;

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

        public EmployeeController(IEmployeeService service, IAccountService accounts, IAuthenticationManager authenticationManager)
        {
            this.service = service;
            this.authenticationManager = authenticationManager;
            this.accounts = accounts;
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
        public async Task<ActionResult> Login(LoginViewModel model)
        {
            if (!ModelState.IsValid) return View(model);
            var result = await accounts.AuthenticateUserAsync(new EmployeeDTO
            {
                Email = model.Email,
                Password = model.Password
            });

            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                model.Password = string.Empty;
                return View(model);
            }
            authenticationManager.SignOut();
            authenticationManager.SignIn(new AuthenticationProperties
            {
                IsPersistent = true
            }, result.GetContent());

            return RedirectToAction("List", "Employee");
        }

        public ActionResult Logout()
        {
            authenticationManager.SignOut();
            return RedirectToAction("List");
        }

        [HttpGet]
        [Authorize(Roles = "ChiefEditor")]
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
        [Authorize(Roles = "ChiefEditor")]
        public async Task<ActionResult> Create(EmployeeCreateModel model)
        {
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            var result = await accounts.CreateAccountAsync(user);

            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            if (model.ChiefEditor)
            {
                authenticationManager.SignOut();
            }

            TempData["Message"] = result.GetContent();


            return RedirectToAction("List");
        }

        [HttpGet]
        [Authorize(Roles = "ChiefEditor")]
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
        [Authorize(Roles = "ChiefEditor")]
        public async Task<ActionResult> Edit(EmployeeEditModel model)
        {
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }
            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            var result = await accounts.EditAccountAsync(user);
            if (!result.IsSuccessful)
            {
                ModelState.AddModelError("", result.GetFailureMessage());
                model.TypeList = GetEmployeeTypeSelectList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            if (model.ChiefEditor)
            {
                authenticationManager.SignOut();
            }

            TempData["Message"] = result.GetContent();

            return RedirectToAction("List");
        }

        [AjaxOnly]
        [Authorize(Roles = "ChiefEditor")]
        public async Task<ActionResult> Delete(int? id)
        {
            var result = await accounts.DeleteAccountAsync(id);

            return Json(new
            {
                isSuccessful = result.IsSuccessful,
                message = result.IsSuccessful ? result.GetContent() : result.GetFailureMessage()
            }, JsonRequestBehavior.AllowGet);
        }
    }
}