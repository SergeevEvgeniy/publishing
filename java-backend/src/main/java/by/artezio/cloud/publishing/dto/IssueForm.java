package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Igor Kuzmin
 */
public class IssueForm {

    private int publishingId;

    private List<Integer> articlesId;

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
    public List<Integer> getArticlesId() {
        return articlesId;
    }

    /**
     *
     * @param articlesId id
     */
    public void setArticlesId(final List<Integer> articlesId) {
        this.articlesId = articlesId;
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
