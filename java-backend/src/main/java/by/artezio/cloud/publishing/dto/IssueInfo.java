package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Объект DTO для страницы списка номеров.
 * @author Igor Kuzmin
 */
public class IssueInfo {

    private int issueId;

    private String publishingTitle;

    private String number;

    private LocalDate localDate;

    private int numberOfArticle;

    private LocalDateTime publicationDate;

    private boolean published;

    /**
     * Получение названия журнала/газеты.
     * @return - название журнала/газеты.
     * */
    public String getPublishingTitle() {
        return publishingTitle;
    }

    /**
     * Установка названия журнала/газеты.
     * @param publishingTitle - название журнала/газеты.
     * */
    public void setPublishingTitle(final String publishingTitle) {
        this.publishingTitle = publishingTitle;
    }

    /**
     * Установка номера журнала/газуты.
     * @param number - номер журнала/газеты.
     * */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Получение номера журнала/газеты.
     * @return - номер журнала/газеты.
     * */
    public String getNumber() {
        return number;
    }

    /**
     * Получение даты рассылки системой.
     * @return - дата рассылки системой.
     * */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Установка даты рассылки системой.
     * @param localDate - дата рассылки системой.
     * */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Получение количества статей в номере.
     * @return - количества статей в номере.
     * */
    public int getNumberOfArticle() {
        return numberOfArticle;
    }

    /**
     * Установка количества статей в номере.
     * @param numberOfArticle - количества статей в номере.
     * */
    public void setNumberOfArticle(final int numberOfArticle) {
        this.numberOfArticle = numberOfArticle;
    }

    /**
     * Установка даты публикации.
     * @param publicationDate - дата публикации.
     * */
    public void setPublicationDate(final LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Получение даты публикации.
     * @return - дата публикации.
     * */
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    /**
     * Получение информации опубликован номер или нет.
     * @return - флаг индикации публикации.
     * */
    public boolean isPublished() {
        return published;
    }

    /**
     * Метод установки флага публикации.
     * @param published - флаг индикации публикации.
     * */
    public void setPublished(final boolean published) {
        this.published = published;
    }

    /**
     * Метод установки id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @param issueId - id {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    public void setIssueId(final int issueId) {
        this.issueId = issueId;
    }

    /**
     * Метод получения id {@link by.artezio.cloud.publishing.domain.Issue}.
     * @return - id {@link by.artezio.cloud.publishing.domain.Issue}
     * */
    public int getIssueId() {
        return issueId;
    }

}
