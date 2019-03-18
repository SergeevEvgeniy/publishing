package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Объект DTO для формы создания/редактирования и для таблицы
 * на главной странице номеров.
 * @author Igor Kuzmin
 */
public class IssueInfo {

    private int publishingId;

    private String publishingTitle;

    private List<Integer> articlesId;

    private String number;

    private LocalDate localDate;

    private boolean published;

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

    /**
     * @param publishingTitle установка заголовка издания
     * */
    public void setPublishingTitle(final String publishingTitle) {
        this.publishingTitle = publishingTitle;
    }

    /**
     * @return  publishingTitle получение заголовка издания
     * */
    public String getPublishingTitle() {
        return publishingTitle;
    }

    /**
     * @param published установка статуса в публикацию
     * */
    public void setPublished(final boolean published) {
        this.published = published;
    }

    /**
     * @return published получение статуса номера
     * */
    public boolean isPublished() {
        return published;
    }

}
