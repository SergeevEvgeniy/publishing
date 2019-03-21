using System.Web.Mvc;
using System.Web.Security;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;

namespace CloudPublishing.Controllers
{
    /// <inheritdoc />
    /// <summary>
    ///     Контроллер, реализующий функционал аутентификации
    /// </summary>
    public class AccountController : Controller
    {
        private readonly IAccountService accounts;

        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр контроллера используя реализацию сервиса аккаунтов <see cref="IAccountService" />
        /// </summary>
        /// <param name="accounts"></param>
        public AccountController(IAccountService accounts)
        {
            this.accounts = accounts;
        }

        /// <summary>
        ///     Метод для получения представления аутентификации
        /// </summary>
        /// <returns>Представление для входа в систему</returns>
        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        /// <summary>
        ///     Метод реализующий аутентификацию и проверку введенных данных
        /// </summary>
        /// <param name="model">Данные формы входа</param>
        /// <param name="returnUrl">Адрес, с которого произошло перенаправление на страницу входа</param>
        /// <returns>
        ///     Переход по ссылке из
        ///     <see>
        ///         <cref>returnUrl</cref>
        ///     </see>
        ///     или возврат представления с сообщением об ошибке
        /// </returns>
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

            return Url.IsLocalUrl(returnUrl)
                ? (ActionResult) Redirect(returnUrl)
                : RedirectToAction("List", "Employee");
        }

        /// <summary>
        ///     Метод для реализации выхода пользователя из системы
        /// </summary>
        /// <returns>Перенаправление на страницу со списком сотрудников</returns>
        public ActionResult LogOut()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("List", "Employee");
        }
    }
}