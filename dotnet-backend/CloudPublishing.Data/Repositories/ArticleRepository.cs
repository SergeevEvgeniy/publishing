using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
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
            var rnd = new Random();
            return await Task.FromResult(new Dictionary<string, int>
            {
                {"Газета \"Заря\"", rnd.Next(0, 100)},
                {"Журнал \"Утро\"", rnd.Next(0, 100)}
            });
        }

        public async Task<IDictionary<string, int>> GetJournalistArticleCountByTopics(int journalistId)
        {
            var rnd = new Random();
            return await Task.FromResult(new Dictionary<string, int>
            {
                {"Здоровое утро", rnd.Next(0, 100)},
                {"Дача, Огород", rnd.Next(0, 100)}
            });
        }
    }
}