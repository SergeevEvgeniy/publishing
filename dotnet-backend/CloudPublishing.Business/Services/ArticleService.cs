using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using Newtonsoft.Json.Linq;

namespace CloudPublishing.Business.Services
{
    /// <summary>
    /// Сервис статей
    /// </summary>
    public class ArticleService : IArticleService
    {
        private readonly HttpClient client;
        private const string ArticleServiceUri = "http://10.99.33.221:8080/publishing/api/";


        public ArticleService()
        {
            client = new HttpClient();
        }

        /// <inheritdoc/>
        public ArticleDTO GetArticleById(int id)
        {
            var uri = $"{ArticleServiceUri}/article?articleId={id}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<ArticleDTO>(response);
        }

        /// <inheritdoc/>
        public IEnumerable<ArticleDTO> GetArticlesByTopicAndPublishingId(int publishingId, int topicId)
        {
            var uri = $"{ArticleServiceUri}/articles?publishingId={publishingId}&topicId={topicId}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(response);
        }

        /// <inheritdoc/>
        public IEnumerable<ArticleDTO> GetUnpublishedArticles(int publishingId, int topicId, int authorId)
        {
            var uri = $"{ArticleServiceUri}/articles?publishingId={publishingId}&topicId={topicId}&authorId={authorId}";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<IEnumerable<ArticleDTO>>(response);
        }

        /// <inheritdoc/>
        public IEnumerable<int> GetAuthorList(int publishingId, int topicId)
        {
            var uri = $"{ArticleServiceUri}/authorsIdList?publishingId={publishingId}&topicId={topicId}";
            var response = client.GetStringAsync(uri).Result;
            var articleList = JsonConvert.DeserializeObject<IEnumerable<int>>(response);

            return articleList;
        }

        /// <inheritdoc/>
        public bool CheckPublicationArticle(int articleId)
        {
            var uri = $"{ArticleServiceUri}/boolean?articleId={articleId}&unpublished=false";
            var response = client.GetStringAsync(uri).Result;
            return JsonConvert.DeserializeObject<bool>(response);
        }

        /// <inheritdoc/>
        public JournalistStatisticsDTO GetJournalistStatistics(int id)
        {
            var task = client.GetStringAsync(ArticleServiceUri + "/article/statistics/" + id);
            var jObject = JObject.Parse(task.Result);
            
            return new JournalistStatisticsDTO
            {
                ArticleCount = (int)jObject["articleCount"],
                ArticleCountByTopics = ((JObject)jObject["articleCountByTopics"])
                    .Select<KeyValuePair<string, JToken>, KeyValuePair<string, int>>(x =>
                        new KeyValuePair<string, int>(x.Key, (int)x.Value)).ToDictionary(x => x.Key, x => x.Value),
                ArticleCountByPublishing = ((JObject)jObject["articleCountByPublishing"])
                    .Select<KeyValuePair<string, JToken>, KeyValuePair<string, int>>(x =>
                        new KeyValuePair<string, int>(x.Key, (int)x.Value)).ToDictionary(x => x.Key, x => x.Value)
            };
        }
    }
}
