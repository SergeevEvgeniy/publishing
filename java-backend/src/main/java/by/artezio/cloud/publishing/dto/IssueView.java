package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Article;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


/**
 * Объект DTO содержащий данные для просмотра номеров.
 * @author Igor Kuzmin
 */
public class IssueView {

    private Integer publishingId;

    private String publishingTitle;

    private String number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    private List<String> advertisingPath;

    private List<ArticleDto> articles;

    /**
     * Метод для установки id журнала/гезеты.
     * @param publishingId - id журнала/гезеты.
     * */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Метод для получения id журнала/гезеты.
     * @return - id журнала/гезеты.
     * */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * Метод для установки названия журнала/гезеты.
     * @param publishingTitle - название журнала/гезеты.
     * */
    public void setPublishingTitle(final String publishingTitle) {
        this.publishingTitle = publishingTitle;
    }

    /**
     * Метод для получения названия журнала/гезеты.
     * @return - название журнала/гезеты.
     * */
    public String getPublishingTitle() {
        return publishingTitle;
    }

    /**
     * Установка номера для журнала/газеты.
     * @param number - номер журнала/газеты.
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Получение номера журнала/газеты.
     * @return - номер журнала/газеты.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Установка даты рассылки системой.
     * @param localDate - дата рассылки системой.
     * */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Получение даты рассылки системой.
     * @return - дата рассылки системой.
     * */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Установка списка путей для рекламы.
     * @param advertisingPath - список путей для рекламы.
     * */
    public void setAdvertisingPath(final List<String> advertisingPath) {
        this.advertisingPath = advertisingPath;
    }

    /**
     * Получения списка путей для рекламы.
     * @return - список путей для рекламы.
     * */
    public List<String> getAdvertisingPath() {
        return advertisingPath;
    }

    /**
     * Установка информации по статьям которые добавлены в номер.
     * @param articles - {@link Article}.
     * */
    public void setArticles(final List<ArticleDto> articles) {
        this.articles = articles;
    }

    /**
     * Метод для получения списка статей.
     * @return - список статей.
     * */
    public List<ArticleDto> getArticles() {
        return articles;
    }

}
