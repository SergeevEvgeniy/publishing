﻿using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.Enums;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Util;
using Microsoft.Owin.Security;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    public class EmployeeController : Controller
    {
        private readonly IEmployeeService service;
        private readonly IAccountService accounts;
        private readonly IAuthenticationManager authenticationManager;
        
        private readonly IMapper mapper;

        public EmployeeController(IEmployeeService service, IAccountService accounts, IAuthenticationManager authenticationManager)
        {
            this.service = service;
            this.authenticationManager = authenticationManager;
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
            if (TempData["Message"] != null) ViewBag.Message = TempData["Message"].ToString();
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
        public async Task<ActionResult> Login(LoginViewModel model)
        {
            if (!ModelState.IsValid) return View(model);
            var claims = await accounts.AuthenticateUserAsync(new EmployeeDTO
            {
                Email = model.Email,
                Password = model.Password
            });

            if (claims == null)
            {
                ModelState.AddModelError("", "Введены неверные данные");
                model.Password = string.Empty;
                return View(model);
            }
            authenticationManager.SignOut();
            authenticationManager.SignIn(new AuthenticationProperties
            {
                IsPersistent = model.CheckOut
            }, claims);

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
                TypeList = GetEmployeeTypeList(),
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
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }

            var user = mapper.Map<EmployeeCreateModel, EmployeeDTO>(model);

            var result = await accounts.CreateAccountAsync(user);

            //if (!result.IsSuccessful)
            //{
            //    ModelState.AddModelError("", result.GetFailureMessage());
            //    model.TypeList = GetEmployeeTypeSelectList();
            //    model.EducationList = GetEmployeeEducationList();
            //    return View(model);
            //}

            if (model.ChiefEditor)
            {
                authenticationManager.SignOut();
            }

            TempData["Message"] = result;


            return RedirectToAction("List");
        }

        [HttpGet]
        [Authorize(Roles = "ChiefEditor")]
        public ActionResult Edit(int? id)
        {
            if (id == null) return null;

            var result = service.GetEmployeeById(id.Value);
            if (result == null) return null;

            var model = mapper.Map<EmployeeDTO, EmployeeEditModel>(result);
            model.TypeList = GetEmployeeTypeList();
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
                model.TypeList = GetEmployeeTypeList();
                model.EducationList = GetEmployeeEducationList();
                return View(model);
            }
            var user = mapper.Map<EmployeeEditModel, EmployeeDTO>(model);
            var result = await accounts.EditAccountAsync(user);
            //if (!result.IsSuccessful)
            //{
            //    ModelState.AddModelError("", result.GetFailureMessage());
            //    model.TypeList = GetEmployeeTypeSelectList();
            //    model.EducationList = GetEmployeeEducationList();
            //    return View(model);
            //}

            if (model.ChiefEditor)
            {
                authenticationManager.SignOut();
            }

            TempData["Message"] = result;

            return RedirectToAction("List");
        }

        [AjaxOnly]
        [Authorize(Roles = "ChiefEditor")]
        public async Task<ActionResult> Delete(int? id)
        {
            //var result = await accounts.DeleteAccountAsync(id);

            //return Json(new
            //{
            //    isSuccessful = result.IsSuccessful,
            //    message = result.IsSuccessful ? result.GetContent() : result.GetFailureMessage()
            //}, JsonRequestBehavior.AllowGet);

            return Json(new
            {
                isSuccessful = false,
                message = "Not implemented"
            }, JsonRequestBehavior.AllowGet);
        }
    }
}