using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Reviews.ViewModels;
using System.Collections.Generic;
using System.Web.Mvc;
using CloudPublishing.Business.Constants;
using CloudPublishing.Util.Profiles;
using CloudPublishing.Util.Attributes;

namespace CloudPublishing.Controllers
{
    /// <summary>
    /// Контроллер рецензий
    /// </summary>
    [HandleException]
    [RoutePrefix("Review")]
    [Authorize(Roles = EmployeeRole.Editor + "," + EmployeeRole.ChiefEditor)]
    public class ReviewController : Controller
    {
        private IReviewService reviewService;
        private IPublishingService publishingService;
        private IEmployeeService employeeService;
        private IArticleService articleService;
        private IMapper mapper;

        /// <summary>
        /// Конструктор контроллера
        /// </summary>
        /// <param name="reviewService">Сервис рецензий</param>
        /// <param name="publishingService">Сервис публикаций</param>
        /// <param name="employeeService">Сервис сотрудников</param>
        /// <param name="articleService">Сервис статей</param>
        public ReviewController(IReviewService reviewService, IPublishingService publishingService,
            IEmployeeService employeeService, IArticleService articleService)
        {
            this.reviewService = reviewService;
            this.publishingService = publishingService;
            this.employeeService = employeeService;
            this.articleService = articleService;

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewMapProfile())).CreateMapper();
        }

        // GET: Review
        /// <summary>
        /// Метод обработки запроса на получение главной страницы рецензий
        /// </summary>
        /// <returns>Возвращает представление, содержащее список рецензий</returns>
        [HttpGet]
        public ActionResult Index()
        {
            var list = mapper.Map<IEnumerable<DetailedReviewModel>>(reviewService.CreateDetailedReviewList(GetUserId()));
            return View(list);
        }

        /// <summary>
        /// Метод обработки запроса на получение страницы создания рецензии
        /// </summary>
        /// <returns>Возвращает представление создания рецензий</returns>
        [HttpGet]
        public ActionResult Create()
        {
            var pl = publishingService.GetPublishings();
            var model = new CreateReviewModel()
            {
                PublishingList = mapper.Map<IEnumerable<PublishingModel>>(pl),
                ReviwerId = GetUserId()
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
            if (ModelState.IsValid)
            {
                reviewService.CreateReview(mapper.Map<ReviewDTO>(review));
            }
            return Redirect("/Review/Index");
        }

        /// <summary>
        /// Метод обработки запроса на получение списка рубрик журнала
        /// </summary>
        /// <param name="id">Id журнала, рубрики которого требуется получить</param>
        /// <returns>Частичное представление, содержащее option-ы, ключом которых является id рубрики, значением - наименование</returns>
        [HttpGet]
        [Route("GetTopicList/{id:int}")]
        public ActionResult GetTopicList(int id)
        {
            var topicList = mapper.Map<IEnumerable<TopicModel>>(publishingService.GetPublishingTopics(id));
            return PartialView(topicList);
        }

        /// <summary>
        /// Метод обработки запроса на получение списка авторов, публиковавшихся в рубрике журнала
        /// </summary>
        /// <param name="publishingId">Id журнала</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Частичное представление, содержащее option-ы, ключом которых является id сотрудника, значением - полное имя</returns>
        [HttpGet]
        [Route("GetAuthorList/{publishingId:int}/{topicId:int}")]
        public ActionResult GetAuthorList(int publishingId, int topicId)
        {
            var authorList = mapper.Map<IEnumerable<AuthorModel>>(articleService.GetAuthorList(publishingId, topicId));
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
        [Route("GetArticleList/{publishingId:int}/{topicId:int}/{authorId:int}")]
        public ActionResult GetArticleList(int publishingId, int topicId, int authorId)
        {
            var articleList = mapper.Map<IEnumerable<ArticleModel>>
               (articleService.GetUnpublishedArticles(publishingId, topicId, authorId));
            return PartialView(articleList);
        }

        /// <summary>
        /// Метод обработки запроса на получение текста статьи
        /// </summary>
        /// <param name="id">Id статьи</param>
        /// <returns>Текст статьи в формате Json</returns>
        [HttpGet]
        [Route("GetArticleContent/{id:int}")]
        public ActionResult GetArticleContent(int id)
        {
            return Json(articleService.GetArticleById(id).Content, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// Метод обработки запроса на просмотр рецензии
        /// </summary>
        /// <param name="model">Модель, содержащая Id сотрудника, написавшего рецензию и Id статьи, на которую написана рецензия</param>
        /// <returns>Представление просмотра, содержащее текст рецензии</returns>
        [HttpGet]
        public ActionResult Details(ReviewKeyModel model)
        {
            var review = mapper.Map<ReviewModel>(reviewService.GetReview(model.ArticleId, model.ReviwerId));
            return View(review);
        }

        /// <summary>
        /// Метод обработки запроса получения страницы редактирования рецензии
        /// </summary>
        /// <param name="model">Модель, содержащая Id сотрудника, написавшего рецензию и Id статьи, на которую написана рецензия</param>
        /// <returns>Представление редактирования</returns>
        [HttpGet]
        public ActionResult Edit(ReviewKeyModel model)
        {
            if (ModelState.IsValid)
            {
                var review = mapper.Map<ReviewModel>(reviewService.GetReview(model.ArticleId, model.ReviwerId));
                return View(review);
            }
            return Redirect("/Review/Index");
        }

        /// <summary>
        /// Метод обработки запроса редактирования рецензии
        /// </summary>
        /// <param name="review">Модель рецензии</param>
        /// <returns>Перенаправляет на страницу со списком рецензий</returns>
        [HttpPost]
        public ActionResult Edit(ReviewModel review)
        {
            if (ModelState.IsValid)
            {
                reviewService.UpdateReview(mapper.Map<ReviewDTO>(review));
            }
            return Redirect("/Review/Index");
        }

        /// <summary>
        /// Метод обработки запроса на удаление рецензии
        /// </summary>
        /// <param name="model">Модель, содержащая Id сотрудника, написавшего рецензию и Id статьи, на которую написана рецензия</param>
        /// <returns>Представление подтверждения удаления</returns>
        [HttpGet]
        public ActionResult Delete(ReviewKeyModel model)
        {
            var isPublished = articleService.CheckPublicationArticle(model.ArticleId);
            var deleteModel = new DeleteReviewModel {
                ArticleId = model.ArticleId,
                ReviwerId = model.ReviwerId,
                IsPublished = isPublished
            };
            return View(deleteModel);
        }

        /// <summary>
        /// Метод обработки запроса на подтверждение удаления рецензии
        /// </summary>
        /// <param name="model">Модель, содержащая Id сотрудника, написавшего рецензию и Id статьи, на которую написана рецензия</param>
        [HttpPost]
        public ActionResult ConfirmDeletion(ReviewKeyModel model)
        {
            var isPublished = articleService.CheckPublicationArticle(model.ArticleId);
            if (!isPublished)
            {
                reviewService.DeleteReview(model.ArticleId, model.ReviwerId);
            }
            return Redirect("/Review/Index");
        }

        [NonAction]
        private int GetUserId()
        {
            return employeeService.GetEmployeeId(User.Identity.Name);
        }
    }
}