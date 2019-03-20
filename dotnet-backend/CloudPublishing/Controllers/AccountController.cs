using System.Web.Mvc;
using System.Web.Security;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Controllers
{
    public class AccountController : Controller
    {
        private readonly IAccountService accounts;

        public AccountController(IAccountService accounts)
        {
            this.accounts = accounts;
        }

        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult LogIn(LoginViewModel model, string returnUrl)
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

            return Redirect(returnUrl);
        }

        public ActionResult LogOut()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("List", "Employee");
        }
    }
}