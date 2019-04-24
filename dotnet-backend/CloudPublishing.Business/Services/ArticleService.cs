using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;

namespace CloudPublishing.Business.Services
{
    /// <summary>
    /// Сервис статей
    /// </summary>
    class ArticleService : IArticleService
    {
        private IEmployeeService employeeService;
        private HttpClient client;
        private const string ArticleServiceURI = "http://10.99.33.221:8080/publishing/api/";

        /// <summary>
        /// Конструтор сервиса
        /// </summary>
        /// <param name="employeeService">Сервис сотрудников</param>
        public ArticleService(IEmployeeService employeeService)
        {
            this.employeeService = employeeService;
            client = new HttpClient();
        }

        /// <summary>
        /// Метод получения статьи по ее Id
        /// </summary>
        /// <param name="id">Id статьи</param>
        /// <returns>DTO статьи</returns>
        public ArticleDTO GetArticleById(int id)
        {
            var uri = $"{ArticleServiceURI}/article?articleId={id}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<ArticleDTO>(response);
        }

        /// <summary>
        /// Метод получения списка статей по Id рубрики и Id публикации
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Коллекция DTO статей</returns>
        public IEnumerable<ArticleDTO> GetArticlesByTopicAndPublishingId(int publishingId, int topicId)
        {
            var uri = $"{ArticleServiceURI}/articles?publishingId={publishingId}&topicId={topicId}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(response);
        }

        /// <summary>
        /// Метод получения списка неопубликованных статей
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Коллекция DTO статей</returns>
        public IEnumerable<ArticleDTO> GetUnpublishedArticles(int publishingId, int topicId, int authorId)
        {
            var uri = $"{ArticleServiceURI}/articles?publishingId={publishingId}&topicId={topicId}&authorId={authorId}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(response);
        }

        /// <summary>
        /// Метод получения списка авторов статей
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Коллекция DTO сотрудников</returns>
        public IEnumerable<EmployeeDTO> GetAuthorList(int publishingId, int topicId)
        {
            var uri = $"{ArticleServiceURI}/authorsIdList?publishingId={publishingId}&topicId={topicId}";
            var response = client.GetStringAsync(uri).Result;
            var articleList = JsonConvert.DeserializeObject<IEnumerable<int>>(response);

            return articleList.Select(x =>
            {
                return employeeService.GetEmployeeById(x);
            });
        }

        /// <summary>
        /// Метод проверки, опубликована ли статья
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <returns>Логическое значение, false - если не опубликована и true, если опубликована</returns>
        public bool CheckPublicationArticle(int articleId)
        {
            var uri = $"{ArticleServiceURI}/boolean?articleId={articleId}&unpublished=false";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<bool>(response);
        }
    }
}
