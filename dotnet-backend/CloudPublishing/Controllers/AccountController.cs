using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using CloudPublishing.Models.Accounts.Identity;
using CloudPublishing.Models.Accounts.Identity.Managers;
using CloudPublishing.Models.Employees.ViewModels;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;

namespace CloudPublishing.Controllers
{
    public class AccountController : Controller
    {
        private EmployeeUserManager UserManager => HttpContext.GetOwinContext().GetUserManager<EmployeeUserManager>();

        private IAuthenticationManager AuthenticationManager => HttpContext.GetOwinContext().Authentication;

        public ActionResult Login(string url)
        {
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