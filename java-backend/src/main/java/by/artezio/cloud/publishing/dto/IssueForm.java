package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Объект DTO для формы создания/редактирования номеров.
 * @author Igor Kuzmin
 */
public class IssueForm {

    private int publishingId;

    private List<Integer> articlesId;

    private String number;

    private LocalDate localDate;

    /**
     * @return publishingId id публикации полученное с формы
     */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * @param publishingId установка id публикации номера
     */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * @return {@link List} id всех статей в номере
     */
    public List<Integer> getArticlesId() {
        return articlesId;
    }

    /**
     * @param articlesId {@link List} для установки id статей для номера
     */
    public void setArticlesId(final List<Integer> articlesId) {
        this.articlesId = articlesId;
    }

    /**
     * @return number значение номера
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number установка номера
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * @return localDate дата когда номер будет опубликован системой
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * @param localDate localDate установка даты публикации системой данного номера
     */
    public void setLocalDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

}
