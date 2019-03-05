package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляет строку из таблицы "Review".
 *
 * @author Denis Shubin
 */
public class Review {

    private int articleId;
    private int reviewerId;
    private String content;
    private boolean approved;

    /**
     * Конструктор, создаст объект с полями по-умолчанию.
     */
    public Review() {
    }

    /**
     * Конструктор, создаст объект с указанными значениями полей.
     *
     * @param articleId  int
     * @param reviewerId int
     * @param content    String
     * @param approved   boolean
     */
    public Review(final int articleId, final int reviewerId, final String content, final boolean approved) {
        this.articleId = articleId;
        this.reviewerId = reviewerId;
        this.content = content;
        this.approved = approved;
    }

    /**
     * Возвращает id статьи.
     * @return int
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     * Устанавливает id статьи.
     * @param articleId int
     */
    public void setArticleId(final int articleId) {
        this.articleId = articleId;
    }

    /**
     * Возвращает id рецензента.
     * @return int
     */
    public int getReviewerId() {
        return reviewerId;
    }

    /**
     * Устанавливает id рецензента.
     * @param reviewerId int
     */
    public void setReviewerId(final int reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * Возвращает текст рецензии.
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает текст рецензии.
     * @param content String
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Возвращает true, если рецензент допускает статью в печать, иначе - false.
     * @return boolean
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Устанавливает допуск статьи в печать.
     * @param approved boolean
     */
    public void setApproved(final boolean approved) {
        this.approved = approved;
    }
}
