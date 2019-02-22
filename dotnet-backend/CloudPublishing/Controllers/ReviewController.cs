using CloudPublishing.Models.Reviews;
using CloudPublishing.Models.Reviews.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CloudPublishing.Controllers
{
    // [Authorize(Roles = "redactor")]
    public class ReviewController : Controller
    {
        private IReviewRepository repo;

        public ReviewController(IReviewRepository repo)
        {
            this.repo = repo;
        }

        // GET: Review
        public ActionResult Index()
        {
            // Получение текущего пользователя
            int userId = 1;

            List<Review> reviews = repo.GetUserReviews(userId).ToList();

            // Запрос за статьями по полю article_id
            // Пример модели для работы
            List<ViewModel> reviewsList = new List<ViewModel>();
            reviewsList.Add(new ViewModel
            {
                Publishing = "Садоводство",
                Topic = "Урожай",
                Author = "Коваленко М.О.",
                Article = "Садим тыкву, садим вместе",
                ArticleId = 1,
                Approved = false
            });
            reviewsList.Add(new ViewModel
            {
                Publishing = "Садоводство",
                Topic = "Урожай",
                Author = "Петров П.П.",
                Article = "Огурцы - кладовая витаминов",
                ArticleId = 2,
                Approved = false
            });
            reviewsList.Add(new ViewModel
            {
                Publishing = "Садоводство",
                Topic = "Дача",
                Author = "Коваленко М.О.",
                Article = "Готовим на природе",
                ArticleId = 3,
                Approved = true
            });

            return View(reviewsList);
        }

        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        // Методы обработки запросов для построения списков

        [HttpPost]
        public ActionResult Create(Review review)
        {
            if(!ModelState.IsValid)
            {
                return View("InvalidModel");
            }
            repo.Create(review);
            return View("Index");
        }

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
        }


    }
}