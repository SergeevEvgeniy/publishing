package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;

/**
 *
 * @author rezerv
 */
public class IssueForm {

    private int publishingId;

    private int articleId;

    private String number;

    private LocalDate localDate;

    /**
     *
     * @return publishingId
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     *
     * @param publishingId id
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     *
     * @return articleId
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     *
     * @param articleId id
     */
    public void setArticleId(final int articleId) {
        this.articleId = articleId;
    }

    /**
     *
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number number of Issue
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     *
     * @return localDate
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     *
     * @param localDate localDate
     */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

}
