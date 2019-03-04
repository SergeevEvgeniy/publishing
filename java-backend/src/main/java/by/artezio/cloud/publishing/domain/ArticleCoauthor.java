package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляющий запись из таблицы "article_coauthors".
 */
public class ArticleCoauthor {

    private Integer articleId;
    private Integer employeeId;

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
    public void setArticleId(final Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * Получение идентификатора соавтора.
     *
     * @return идентификатор соавтора
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Установка идентификатора соавтора.
     *
     * @param employeeId идентификатор соавтора
     */
    public void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }
}
