using System;
using System.Collections.Generic;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;

namespace CloudPublishing.Business.Services
{
    public class FakeArticleService : IJournalistStatisticsService
    {
        public JournalistStatisticsDTO GetJournalistStatistics(int id)
        {
            var rnd = new Random();

            return new JournalistStatisticsDTO
            {
                ArticleCount = rnd.Next(10,100),
                ArticleCountByPublishing = new Dictionary<string, int>
                {
                    {"Рога и копыта", rnd.Next(10,100)}
                },
                ArticleCountByTopics = new Dictionary<string, int>
                {
                    {"Садоводство", rnd.Next(10,100)},
                    {"Скотоводство", rnd.Next(10,100)}
                }
            };
        }
    }
}