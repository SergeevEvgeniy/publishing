using CloudPublishing.Business.DTO;
using System.Collections.Generic;

namespace CloudPublishing.Business.Services.Interfaces
{
    public interface IArticleService
    {
        /// <summary>
        /// Метод получения статьи по ее Id
        /// </summary>
        /// <param name="id">Id статьи</param>
        /// <returns>DTO статьи</returns>
        ArticleDTO GetArticleById(int id);

        /// <summary>
        /// Метод получения списка статей по Id рубрики и Id публикации
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Коллекция DTO статей</returns>
        IEnumerable<ArticleDTO> GetArticlesByTopicAndPublishingId(int topicId, int publishingId);

        /// <summary>
        /// Метод получения списка неопубликованных статей
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <param name="authorId">Id автора</param>
        /// <returns>Коллекция DTO статей</returns>
        IEnumerable<ArticleDTO> GetUnpublishedArticles(int topicId, int publishingId, int authorId);

        /// <summary>
        /// Метод получения списка авторов статей
        /// </summary>
        /// <param name="publishingId">Id публикации</param>
        /// <param name="topicId">Id рубрики</param>
        /// <returns>Коллекция DTO сотрудников</returns>
        IEnumerable<int> GetAuthorList(int publishingId, int topicId);

        /// <summary>
        /// Метод проверки, опубликована ли статья
        /// </summary>
        /// <param name="articleId">Id статьи</param>
        /// <returns>Логическое значение, false - если не опубликована и true, если опубликована</returns>
        bool CheckPublicationArticle(int articleId);

        /// <summary>
        /// Получить статистику по журналисту
        /// </summary>
        /// <param name="id">Идентификатор сотрудника</param>
        /// <returns></returns>
        JournalistStatisticsDTO GetJournalistStatistics(int id);
    }
}
