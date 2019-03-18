using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using System.Web.Security;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Util;

namespace CloudPublishing.Controllers
{
    public class EmployeeController : Controller
    {
        private readonly IAccountService accounts;

        private readonly IMapper mapper;
        private readonly IEmployeeService service;

        public EmployeeController(IEmployeeService service, IAccountService accounts)
        {
            this.service = service;
            this.accounts = accounts;
            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new EmployeeMapProfile())).CreateMapper();
        }

        private List<SelectListItem> GetEmployeeTypeList()
        {
            return service.GetEmployeeTypes().Select(x => new SelectListItem
            {
                Value = x.Key,
                Text = x.Value
            }).ToList();
        }

        private List<SelectListItem> GetEmployeeEducationList()
        {
            var list = service.GetEducationList();
            return list.Select(x => new SelectListItem {Text = x.Title, Value = x.Id.ToString()}).ToList();
        }

        [HttpGet]
        public ActionResult List()
        {
            if (TempData["Message"] != null)
            {
                ViewBag.Message = TempData["Message"].ToString();
            }

            var list = service.GetEmployeeList();

            return View(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeViewModel>>(list));
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
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            var user = accounts.AuthenticateUser(model.Email, model.Password);

            if (user == null)
            {
                ModelState.AddModelError("", "Введены неверные данные");
                model.Password = string.Empty;
                return View(model);
            }

            FormsAuthentication.SetAuthCookie(user.Email, model.CheckOut);

            return RedirectToAction("List", "Employee");
        }

        public ActionResult Logout()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("List");
        }

        [HttpGet]
        //[Authorize(Roles = "ChiefEditor")]
        public ActionResult Create()
        {
            var model = new EmployeeCreateModel
            {
                TypeList = GetEmployeeTypeList(),
                EducationList = GetEmployeeEducationList()
            };
            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        //[Authorize(Roles = "ChiefEditor")]
        public ActionResult Create(EmployeeCreateModel model)
        {
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            if (user == null)
            {
                ModelState.AddModelError("", "Ошибка при получении данных пользователя.");
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            accounts.CreateAccount(user);

            TempData["Message"] = "Пользователь " + model.Email + " успешно создан";


            return RedirectToAction("List");
        }

        [HttpGet]
        //[Authorize(Roles = "ChiefEditor")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return null;
            }

            var result = service.GetEmployeeById(id.Value);
            if (result == null)
            {
                return null;
            }

            var model = mapper.Map<EmployeeDTO, EmployeeEditModel>(result);
            model.TypeList = GetEmployeeTypeList();
            model.EducationList = GetEmployeeEducationList();

            if (TempData["Message"] != null)
            {
                ViewBag.Message = TempData["Message"].ToString();
            }

            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        //[Authorize(Roles = "ChiefEditor")]
        public ActionResult Edit(EmployeeEditModel model)
        {
            if (!ModelState.IsValid)
            {
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            if (user == null)
            {
                ModelState.AddModelError("", "Возникла ошибка при получении данных пользователя");
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }
            accounts.EditAccount(user);

            TempData["Message"] = "Данные пользователя " + model.Email + " успешно обновлены";

            return RedirectToAction("List");
        }

        [AjaxOnly]
        //[Authorize(Roles = "ChiefEditor")]
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return Json(new
                {
                    isSuccessful = false,
                    message = "Неверний идентификатор пользователя"
                }, JsonRequestBehavior.AllowGet);
            }
            try
            {
                accounts.DeleteAccount(id.Value);
            }
            catch (ChiefEditorRoleChangeException e)
            {
                return Json(new
                {
                    isSuccessful = false,
                    message = e.Message
                }, JsonRequestBehavior.AllowGet);
            }

            return Json(new
            {
                isSuccessful = true,
                message = "Пользователь успешно удален"
            }, JsonRequestBehavior.AllowGet);
        }
    }
}