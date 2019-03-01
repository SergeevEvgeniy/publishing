using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Reviews.ViewModels;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    // [Authorize(Roles = "redactor")]
    public class ReviewController : Controller
    {
        private IReviewService reviewService;
        private IMapper mapper;

        public ReviewController(IReviewService service)
        {
            reviewService = service;

            MapperConfiguration mapperConfig = new MapperConfiguration(cfg =>
            {
                cfg.CreateMap<DetailedReviewDTO, DetailedReviewVM>();
                cfg.CreateMap<ReviewVM, ReviewDTO>();
                cfg.CreateMap<ReviewDTO, ReviewVM>();
                cfg.CreateMap<PublishingDTO, ShortPublishingModel>();
                cfg.CreateMap<TopicDTO, TopicModel>();
                cfg.CreateMap<EmployeeDTO, AuthorModel>();
                cfg.CreateMap<ArticleDTO, ArticleModel>();
            });
            mapper = mapperConfig.CreateMapper();
        }

        // GET: Review
        public ActionResult Index()
        {
            // Заглушка. Будет заменено на получение id текущего пользователя
            int userId = 1;

            List<DetailedReviewVM> list = mapper.Map<IEnumerable<DetailedReviewDTO>, List<DetailedReviewVM>>
                (reviewService.CreateDetailedReviewList(userId));

            return View(list);

        }

        [HttpGet]
        public ActionResult Create()
        {
            var pl = reviewService.GetPublishingList();
            var model = new CreateReviewModel()
            {
                publishingList = new SelectList(pl, "Id", "Title", 1)
            };
            return View(model);
        }

        [HttpGet]
        public ActionResult GetTopicList(int? publishingId)
        {
            List<TopicModel> topicList = mapper.Map<IEnumerable<TopicDTO>, List<TopicModel>>
                (reviewService.GetTopicList(publishingId));
            return PartialView(topicList);
        }

        [HttpGet]
        public ActionResult GetAuthorList(int? publishingId, int? topicId)
        {
            List<AuthorModel> authorList = mapper.Map<IEnumerable<EmployeeDTO>, List<AuthorModel>>
                (reviewService.GetAuthorList(publishingId, topicId));
            return PartialView(authorList);
        }

        [HttpGet]
        public ActionResult GetArticleList(int? publishingId, int? topicId, int? authorId)
        {
            List<ArticleModel> articleList = mapper.Map<IEnumerable<ArticleDTO>, List<ArticleModel>>
                (reviewService.GetArticleList(publishingId, topicId, authorId));
            return PartialView(articleList);
        }

        [HttpGet]
        public ActionResult GetArticleContent(int? articleId)
        {
            // Заглушка для тестирования
            return Json("Content", JsonRequestBehavior.AllowGet);
        }

        [HttpPost]
        public ActionResult Create(ReviewVM review)
        {
            reviewService.CreateReview(mapper.Map<ReviewVM, ReviewDTO>(review));

            return View("Index");
        }

        public ActionResult Details(int articleId)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            var review = mapper.Map<ReviewDTO, ReviewVM>(reviewService.GetReview(articleId, userId));
            return View(review);
        }

        /*[HttpGet]
        public ActionResult Edit(int articleId)
        {
            // Получение id пользователя
            int userId = 1;
            Review review = repo.Get(articleId, userId);
            if (review == null)
            {
                return HttpNotFound();
            }
            return View(review);
        }

        [HttpPost]
        public ActionResult Edit(Review review)
        {
            if (!ModelState.IsValid)
            {
                return View("InvalidModel");
            }
            repo.Update(review);
            return View("Index");
        }*/


    }
}