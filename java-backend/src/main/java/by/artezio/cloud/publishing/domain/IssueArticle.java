package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляющий записи из таблицы "article_issue".
 */
public class IssueArticle {

    private Integer articleId;
    private Integer issueId;

    /**
     * Получение идентификатора статьи.
     *
     * @return идентификатор статьи
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * Установка идентификатора статьи.
     *
     * @param articleId идентификатор статьи
     */
    public void setArticleId(final int articleId) {
        this.articleId = articleId;
    }

    /**
     * Получение идентификатора выпуска.
     *
     * @return идентификатор выпуска
     */
    public Integer getIssueId() {
        return issueId;
    }

    /**
     * Установка идентификатора выпуска.
     *
     * @param issueId идентификатор выпуска
     */
    public void setIssueId(final int issueId) {
        this.issueId = issueId;
    }
}
