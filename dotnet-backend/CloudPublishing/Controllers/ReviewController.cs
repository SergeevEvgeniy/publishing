using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using CloudPublishing.Models.Reviews.ViewModels;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    // [Authorize(Roles = "redactor")]
    public class ReviewController : Controller
    {
        private IReviewService reviewService;

        public ReviewController(IReviewService service)
        {
            reviewService = service;
        }

        // GET: Review
        public ActionResult Index()
        {
            // Заглушка. Будет заменено на получение id текущего пользователя
            int userId = 1;

            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<DetailedReviewDTO, DetailedReviewVM>()).CreateMapper();
            List<DetailedReviewVM> list = mapper.Map<IEnumerable<DetailedReviewDTO>, List<DetailedReviewVM>>
                (reviewService.CreateDetailedReviewList(userId));

            return View(list);

        }

        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Create(ReviewVM review)
        {
            var mapper = new MapperConfiguration(cfg => cfg.CreateMap<ReviewVM, ReviewDTO>()).CreateMapper();
            reviewService.CreateReview(mapper.Map<ReviewVM, ReviewDTO>(review));

            return View("Index");
        }

        /*[HttpPost]
        public ActionResult Create(Review review)
        {
            if(!ModelState.IsValid)
            {
                return View("InvalidModel");
            }
            repo.Create(review);
            return View("Index");
        }

        // Тут будут методы обработки запросов для построения списков

        public ActionResult Details(int articleId)
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

        [HttpGet]
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