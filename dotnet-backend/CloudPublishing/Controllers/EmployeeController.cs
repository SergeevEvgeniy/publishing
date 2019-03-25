using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Infrastructure;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Employees.ViewModels;
using CloudPublishing.Util;

namespace CloudPublishing.Controllers
{
    /// <inheritdoc />
    /// <summary>
    ///     Контроллер для работы с сотрудниками издательства
    /// </summary>
    [HandleError]
    public class EmployeeController : Controller
    {
        private readonly IMapper mapper;
        private readonly IEmployeeService service;

        /// <inheritdoc />
        /// <summary>
        ///     Создает экземпляр класса, используя реализации сервиса пользователей <see cref="T:CloudPublishing.Business.Services.Interfaces.IEmployeeService" />, сервиса
        ///     аккаунтов <see cref="T:CloudPublishing.Business.Services.Interfaces.IAccountService" /> и маппера <see cref="T:AutoMapper.IMapper" /> для отображения сущностей
        /// </summary>
        /// <param name="service">Сервис, предоставляющий доступ функциям по работе с сотрудниками</param>
        /// <param name="mapper">Маппер для отобраения сущностей пользвоателей на модели для представлений</param>
        public EmployeeController(IEmployeeService service, IMapper mapper)
        {
            this.service = service;
            this.mapper = mapper;
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

        /// <summary>
        ///     Метод для получения списка всех сотрудников издательства
        /// </summary>
        /// <returns>Представление со списком сотрудников издательства</returns>
        [HttpGet]
        public ActionResult List()
        {
            var list = service.GetEmployeeList();

            return View(mapper.Map<IEnumerable<EmployeeDTO>, List<EmployeeViewModel>>(list));
        }

        /// <summary>
        ///     Метод для возврата представления с формой для создания профиля сотрудника
        /// </summary>
        /// <returns>Представление с формой создания</returns>
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

        /// <summary>
        ///     Метод для создания профиля сотрудника. Реализует проверку данных, введенных пользователем, а также добавление
        ///     данных пользователя в базу, если они валидны
        /// </summary>
        /// <param name="model">Данные с формы создания пользователя</param>
        /// <returns>
        ///     Представление с ошибкой, если
        ///     <see>
        ///         <cref>model</cref>
        ///     </see>
        ///     не проходит валидацию, или переадресация к списку
        ///     сотрудников с сообщением о результате создания
        /// </returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "ChiefEditor")]
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

            service.CreateEmployee(user);

            TempData["Message"] = "Пользователь " + model.Email + " успешно создан";

            return RedirectToAction("List");
        }

        /// <summary>
        ///     Метод для возврата страницы с формой редактирования данных сотрудника
        /// </summary>
        /// <param name="id">Идентификатор пользователя</param>
        /// <returns>Представление с формой редактирования</returns>
        [HttpGet]
        [Authorize(Roles = "ChiefEditor")]
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

            return View(model);
        }

        /// <summary>
        ///     Метод для реадктирования данных сотрудника. Реализует валидацию данных, введенных пользователем, а также обновляет
        ///     данные пользователя
        /// </summary>
        /// <param name="model">Данные формы, измененные пользователем</param>
        /// <returns>
        ///     Представление с ошибкой, если
        ///     <see>
        ///         <cref>model</cref>
        ///     </see>
        ///     не проходит валидацию, или переадресация к списку
        ///     сотрудников с сообщением о результате редактирования
        /// </returns>
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "ChiefEditor")]
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

            try
            {
                service.EditEmployee(user);

                TempData["Message"] = "Данные пользователя " + model.Email + " успешно обновлены";
            }
            catch (ChiefEditorRoleChangeException e)
            {
                TempData["Message"] = "Ошибка обновления данных пользователя: " + e.Message;
            }

            return RedirectToAction("List");
        }

        /// <summary>
        ///     Метод для удаления сотрудника
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <returns>
        ///     Если пользователя можно удалить, возвращается JSON-объект с сообщением об успешном удалении и соответствующим
        ///     флагом, иначе JSON-объект с флагом об ошибке удаления и сообщением о причине ошибки
        /// </returns>
        [AjaxOnly]
        [Authorize(Roles = "ChiefEditor")]
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
                service.DeleteEmployee(id.Value);
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