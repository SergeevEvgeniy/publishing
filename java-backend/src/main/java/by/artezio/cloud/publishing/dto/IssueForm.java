package by.artezio.cloud.publishing.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Объект DTO для формы создания/редактирования.
 * @author Igor Kuzmin
 */
public class IssueForm {

    @NotNull
    private Integer publishingId;

    @NotNull
    private List<Integer> articlesId;

    private List<String> advertisingPath;

    @NotBlank
    private String number;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    private boolean published;

    /**
     * Установка id журнала/газеты.
     * @param publishingId - id журнала/газеты.
     * */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Получение id журнала/газеты.
     * @return - id журнала/газеты.
     * */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * Получения списка путей для рекламы.
     * @return - список путей для рекламы.
     * */
    public List<String> getAdvertisingPath() {
        return advertisingPath;
    }


    /**
     * Установка списка путей для рекламы.
     * @param advertisingPath - список путей для рекламы.
     * */
    public void setAdvertisingPath(final List<String> advertisingPath) {
        this.advertisingPath = advertisingPath;
    }

    /**
     * Установка списка id статей добавленных в номер.
     * @param articlesId - список id статей добавленных в номер.
     * */
    public void setArticlesId(final List<Integer> articlesId) {
        this.articlesId = articlesId;
    }

    /**
     * Получение списка id статей добавленных в номер.
     * @return - список id статей добавленных в номер.
     * */
    public List<Integer> getArticlesId() {
        return articlesId;
    }

    /**
     * Получение номера журнала/газеты.
     * @return - номер журнала/газеты.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Установка номера для журнала/газеты.
     * @param number - номер журнала/газеты.
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Получение даты когда номер будет опубликован системой.
     * @return localDate - дата когда номер будет опубликован системой.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Установка даты публикации системой данного номера.
     * @param localDate - дата публикации системой данного номера.
     */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Установка статуса номера.
     * @param published - статус в публикацию.
     * */
    public void setPublished(final boolean published) {
        this.published = published;
    }

    /**
     * Получение статуса номера.
     * @return published - статус номера.
     * */
    public boolean isPublished() {
        return published;
    }

}
