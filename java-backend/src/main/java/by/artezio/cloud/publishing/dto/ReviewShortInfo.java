package by.artezio.cloud.publishing.dto;

/**
 * @author Denis Shubin
 */
public class ReviewShortInfo {

    private int reviewerId;
    private int articleId;
    private String reviewerShortName;
    private String content;

    /**
     * @return id рецензента
     */
    public int getReviewerId() {
        return reviewerId;
    }

    /**
     * @param reviewerId id рецензента
     */
    public void setReviewerId(final int reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * @return id статьи
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     * @param articleId id статьи
     */
    public void setArticleId(final int articleId) {
        this.articleId = articleId;
    }

    /**
     * @return сокращённое полное имя рецензента
     */
    public String getReviewerShortName() {
        return reviewerShortName;
    }

    /**
     * @param reviewerShortName сокращённое полное имя рецензента
     */
    public void setReviewerShortName(final String reviewerShortName) {
        this.reviewerShortName = reviewerShortName;
    }

    /**
     * @return {@link String} содержимое рецензии
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content {@link String} содержимое рецензии
     */
    public void setContent(final String content) {
        this.content = content;
    }
}
