using System.Collections.Generic;
using System.Linq;
using CloudPublishing.Business.DTO;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json.Linq;

namespace CloudPublishing.Business.Services
{
    public class FakeArticleService : IJournalistStatisticsService
    {
        public JournalistStatisticsDTO GetJournalistStatistics(int id)
        {

            var result = JObject.Parse(
                @"{'id':1,'firstName':'Дарья','lastName':'Донцова','middleName':'Аркадьевна','email':'ddontsova@publishing.cloud','sex':'F','birthYear':1952,'articleCount':77,'articleCountByPublishing':{'рога и копыта':77},'articleCountByTopics':{'садоводство':34,'скотоводство':43}}");

            return new JournalistStatisticsDTO
            {
                ArticleCount = (int) result["articleCount"],
                ArticleCountByTopics = ((JObject) result["articleCountByTopics"])
                    .Select<KeyValuePair<string, JToken>, KeyValuePair<string, int>>(x =>
                        new KeyValuePair<string, int>(x.Key, (int) x.Value)).ToDictionary(x => x.Key, x => x.Value),
                ArticleCountByPublishing = ((JObject) result["articleCountByPublishing"])
                    .Select<KeyValuePair<string, JToken>, KeyValuePair<string, int>>(x =>
                        new KeyValuePair<string, int>(x.Key, (int) x.Value)).ToDictionary(x => x.Key, x => x.Value)
            };
        }
    }
}