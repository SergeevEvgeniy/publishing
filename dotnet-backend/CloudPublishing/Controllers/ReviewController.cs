using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Reviews.ViewModels;
using System.Collections.Generic;
using System.Web.Mvc;
using CloudPublishing.Util.Profiles;

namespace CloudPublishing.Controllers
{
    // [Authorize(Roles = "redactor")]
    /// <summary>
    /// Контроллер рецензий
    /// </summary>
    public class ReviewController : Controller
    {
        private IReviewService reviewService;
        private IMapper mapper;

        /// <summary>
        /// Конструктор контроллера
        /// </summary>
        /// <param name="service">Сервис работы с рецензиями</param>
        public ReviewController(IReviewService service)
        {
            reviewService = service;

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewMapProfile())).CreateMapper();
        }

        // GET: Review
        /// <summary>
        /// Метод обработки запроса на получение главной страницы рецензий
        /// </summary>
        /// <returns>Возвращает представление, содержащее список рецензий</returns>
        public ActionResult Index()
        {
            // Заглушка. Будет заменено на получение id текущего пользователя
            int userId = 1;

            List<DetailedReviewModel> list = mapper.Map<IEnumerable<DetailedReviewDTO>, List<DetailedReviewModel>>
                (reviewService.CreateDetailedReviewList(userId));

            return View(list);
        }

        /// <summary>
        /// Метод обработки запроса на получение страницы создания рецензии
        /// </summary>
        /// <returns>Возвращает представление создания рецензий</returns>
        [HttpGet]
        public ActionResult Create()
        {
            var pl = reviewService.GetPublishingList();
            var model = new CreateReviewModel()
            {
                PublishingList = mapper.Map<IEnumerable<PublishingDTO>, List<PublishingModel>>(pl)
            };
            return View(model);
        }

        /// <summary>
        /// Метод обработки запроса создания рецензии
        /// </summary>
        /// <param name="review">Модель рецензии</param>
        /// <returns>Перенаправляет на страницу со списком рецензий</returns>
        [HttpPost]
        public ActionResult Create(ReviewModel review)
        {
            // Будет заменено на получение текущего пользователя
            review.ReviwerId = 1;

            if (ModelState.IsValid)
            {
                reviewService.CreateReview(mapper.Map<ReviewModel, ReviewDTO>(review));
            }

            return Redirect("/Review/Index");
        }

        /// <summary>
        /// Метод обработки запроса на получение списка рубрик журнала
        /// </summary>
        /// <param name="publishingId">Id журнала, рубрики которого требуется получить</param>
        /// <returns>Частичное представление, содержащее option-ы, ключом которых является id рубрики, значением - наименование</returns>
        [HttpGet]
        public ActionResult GetTopicList(int? publishingId)
        {
            List<TopicModel> topicList = mapper.Map<IEnumerable<TopicDTO>, List<TopicModel>>
                (reviewService.GetTopicList(publishingId));
            return PartialView(topicList);
        }

        /// <summary>
        /// Метод обработки запроса на получение списка авторов, публиковавшихся в рубрике журнала
        /// </summary>
        /// <param name="publishingId">Id журнала</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Частичное представление, содержащее option-ы, ключом которых является id сотрудника, значением - полное имя</returns>
        [HttpGet]
        public ActionResult GetAuthorList(int? publishingId, int? topicId)
        {
            List<AuthorModel> authorList = mapper.Map<IEnumerable<EmployeeDTO>, List<AuthorModel>>
                (reviewService.GetAuthorList(publishingId, topicId));
            return PartialView(authorList);
        }

        /// <summary>
        /// Метод обработки запроса на получение списка статей автора в определенной рубрике выбранного журнала
        /// </summary>
        /// <param name="publishingId">Id журнала</param>
        /// <param name="topicId">Id рубрики</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Частичное представление, содержащее option-ы, ключом которых является id статьи, значением - наименование</returns>
        [HttpGet]
        public ActionResult GetArticleList(int? publishingId, int? topicId, int? authorId)
        {
            List<ArticleModel> articleList = mapper.Map<IEnumerable<ArticleDTO>, List<ArticleModel>>
                (reviewService.GetArticleList(publishingId, topicId, authorId));
            return PartialView(articleList);
        }

        /// <summary>
        /// Метод обработки запроса на получение текста статьи
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <returns>Текст статьи в формате Json</returns>
        [HttpGet]
        public ActionResult GetArticleContent(int? articleId)
        {
            // Заглушка для тестирования
            return Json("Content", JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// Метод обработки запроса на просмотр рецензии
        /// </summary>
        /// <param name="articleId">Id статьи, на которую написана рецензия</param>
        /// <returns>Представление просмотра, содержащее текст рецензии</returns>
        public ActionResult Details(int articleId)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            var review = mapper.Map<ReviewDTO, ReviewModel>(reviewService.GetReview(articleId, userId));
            return View(review);
        }

        /// <summary>
        /// Метод обработки запроса получения страницы редактирования рецензии
        /// </summary>
        /// <param name="id">Id статьи, на которую написана рецензия</param>
        /// <returns>Представление редактирования</returns>
        [HttpGet]
        public ActionResult Edit(int id)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            var review = mapper.Map<ReviewDTO, ReviewModel>(reviewService.GetReview(id, userId));

            return View(review);
        }

        /// <summary>
        /// Метод обработки запроса редактирования рецензии
        /// </summary>
        /// <param name="review">Модель рецензии</param>
        /// <returns>Перенаправляет на страницу со списком рецензий</returns>
        [HttpPost]
        public ActionResult Edit(ReviewModel review)
        {
            // Будет заменено на получение id пользователя
            review.ReviwerId = 1;

            if (ModelState.IsValid)
            {
                reviewService.UpdateReview(mapper.Map<ReviewDTO>(review));
            }

            return Redirect("/Review/Index");
        }

        /// <summary>
        /// Метод обработки запроса удаления рецензий
        /// </summary>
        /// <param name="id">Id рецензии</param>
        [HttpPost]
        public void Delete(int id)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            reviewService.DeleteReview(id, userId);
        }
    }
}