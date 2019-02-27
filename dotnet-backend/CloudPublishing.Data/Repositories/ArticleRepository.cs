using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using CloudPublishing.Data.Entities.RestApi;
using CloudPublishing.Data.Interfaces;

namespace CloudPublishing.Data.Repositories
{
    public class ArticleRepository : IArticleRepository
    {
        private readonly HttpClient client;

        public ArticleRepository(HttpClient client)
        {
            this.client = client;
        }

        public async Task<int> GetJournalistArticleCount(int journalistId)
        {
            return await Task.FromResult(new Random().Next(0, 100));
        }

        public async Task<IDictionary<string, int>> GetJournalistArticleCountByPublishings(int journalistId)
        {
            return await Task.FromResult(new Dictionary<string, int>
            {
                {"Газета \"Заря\"", new Random().Next(0, 100)},
                {"Журнал \"Утро\"", new Random().Next(0, 100)}
            });
        }

        public async Task<IDictionary<string, int>> GetJournalistArticleCountByTopics(int journalistId)
        {
            return await Task.FromResult(new Dictionary<string, int>
            {
                {"Здоровое утро", new Random().Next(0, 100)},
                {"Дача, Огород", new Random().Next(0, 100)}
            });
        }

        public async Task<IDictionary<int, int>> GetJournalistFilteredList(int? publishingId, int? issueId, int? topicId, string articleTitle)
        {
            var journalistsInfo = new Dictionary<int, int>
            {
                {13, new Random().Next(0, 100)},
                {14, new Random().Next(0, 100)}
            };
            return await Task.FromResult(journalistsInfo);
        }
    }
}