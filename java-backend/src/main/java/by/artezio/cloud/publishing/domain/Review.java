package by.artezio.cloud.publishing.domain;

public class Review {

    private int articleId;
    private int reviewerId;
    private String content;
    private boolean approved;

    public Review() {
    }

    public Review(int articleId, int reviewerId, String content, boolean approved) {
        this.articleId = articleId;
        this.reviewerId = reviewerId;
        this.content = content;
        this.approved = approved;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
