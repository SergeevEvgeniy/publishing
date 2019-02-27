using System.Collections.Generic;

namespace CloudPublishing.Business.DTO.RestApi
{
    public class JournalistStatisticsDTO
    {
        public JournalistStatisticsDTO()
        {
            PublishingArticles = new Dictionary<string, int>();
            TopicArticles = new Dictionary<string, int>();
        }

        public int Id { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string MiddleName { get; set; }

        public string Email { get; set; }

        public string Sex { get; set; }

        public int ArticleCount { get; set; }

        public IDictionary<string, int> PublishingArticles { get; set; }

        public IDictionary<string, int> TopicArticles { get; set; }
    }
}