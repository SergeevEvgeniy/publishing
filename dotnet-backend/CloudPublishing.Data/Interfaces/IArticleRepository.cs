using System.Collections.Generic;
using System.Threading.Tasks;
using CloudPublishing.Data.Entities.RestApi;

namespace CloudPublishing.Data.Interfaces
{
    public interface IArticleRepository
    {
        Task<int> GetJournalistArticleCount(int journalistId);

        Task<IDictionary<string, int>> GetJournalistArticleCountByPublishings(int journalistId);

        Task<IDictionary<string, int>> GetJournalistArticleCountByTopics(int journalistId);

        Task<IDictionary<int, int>> GetJournalistFilteredList(int? publishingId, int? issueId, int? topicId,
            string articleTitle);
    }
}