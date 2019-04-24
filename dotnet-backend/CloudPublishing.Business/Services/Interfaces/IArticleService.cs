using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IArticleService
    {
        ArticleDTO GetArticleById(int id);

        IEnumerable<ArticleDTO> GetArticlesByTopicAndPublishingId(int topicId, int publishingId);

        IEnumerable<ArticleDTO> GetUnpublishedArticles(int topicId, int publishingId, int authorId);

        IEnumerable<EmployeeDTO> GetAuthorList(int publishingId, int topicId);

        bool CheckPublicationArticle(int articleId);
    }
}
