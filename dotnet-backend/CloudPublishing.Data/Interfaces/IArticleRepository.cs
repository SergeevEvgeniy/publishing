using System.Collections.Generic;
using System.Threading.Tasks;

namespace CloudPublishing.Data.Interfaces
{
    public interface IArticleRepository
    {
        Task<int> GetJournalistArticleCount(int journalistId);

        Task<IDictionary<string, int>> GetJournalistArticleCountByPublishings(int journalistId);

        Task<IDictionary<string, int>> GetJournalistArticleCountByTopics(int journalistId);
    }
}