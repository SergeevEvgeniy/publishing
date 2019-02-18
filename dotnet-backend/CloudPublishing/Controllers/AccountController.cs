using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Models.Identity;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;

namespace CloudPublishing.Controllers
{
    public class AccountController : Controller
    {
        private EmployeeManager UserManager => HttpContext.GetOwinContext().GetUserManager<EmployeeManager>();

        private IAuthenticationManager AuthenticationManager => HttpContext.GetOwinContext().Authentication;

        public async Task<ActionResult> Login(string url)
        {
            //var user = new EmployeeIdentity()
            //{
            //    Type = "J",
            //    Password = "123456",
            //    ChiefEditor = false,
            //    UserName = "evgeniu.chepinsky@mymail.com",
            //    Address = "ул. Пожарная д.13 кв. 51",
            //    BirthYear = 1990,
            //    EducationId = 1,
            //    FirstName = "Евгений",
            //    LastName = "Чепинский",
            //    MiddleName = "Дмитриевич",
            //    Sex = "M"
            //};
            //var result = await UserManager.CreateAsync(user, user.Password);
            ViewBag.ReturnURL = url;
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Login(LoginViewModel model, string url)
        {
            if (!ModelState.IsValid)
            {
                ViewBag.ReturnURL = url;
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
            AuthenticationManager.SignIn(new AuthenticationProperties()
            {
                IsPersistent = model.CheckOut
            }, claim);

            return string.IsNullOrEmpty(url) ? (ActionResult) RedirectToAction("List", "Employee") : Redirect(url);
        }
    }
}