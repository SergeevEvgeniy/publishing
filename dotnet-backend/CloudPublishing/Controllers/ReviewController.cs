using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Reviews.ViewModels;
using CloudPublishing.Util;
using System.Collections.Generic;
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

            mapper = new MapperConfiguration(cfg => cfg.AddProfile(new ReviewMapProfile())).CreateMapper();
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
                PublishingList = mapper.Map<IEnumerable<PublishingDTO>, List<PublishingVM>>(pl)
            };
            return View(model);
        }

        [HttpPost]
        public ActionResult Create(ReviewVM review)
        {
            // Будет заменено на получение текущего пользователя
            review.ReviwerId = 1;

            if (ModelState.IsValid)
            {
                reviewService.CreateReview(mapper.Map<ReviewVM, ReviewDTO>(review));
            }

            return Redirect("/Review/Index");
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

        public ActionResult Details(int articleId)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            var review = mapper.Map<ReviewDTO, ReviewVM>(reviewService.GetReview(articleId, userId));
            return View(review);
        }

        [HttpGet]
        public ActionResult Edit(int id)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            var review = mapper.Map<ReviewDTO, ReviewVM>(reviewService.GetReview(id, userId));

            return View(review);
        }

        [HttpPost]
        public ActionResult Edit(ReviewVM review)
        {
            // Будет заменено на получение id пользователя
            review.ReviwerId = 1;

            if (ModelState.IsValid)
            {
                reviewService.UpdateReview(mapper.Map<ReviewDTO>(review));
            }

            return Redirect("/Review/Index");
        }

        [HttpPost]
        public void Delete(int id)
        {
            // Будет заменено на получение id пользователя
            int userId = 1;

            reviewService.DeleteReview(id, userId);
        }
    }
}