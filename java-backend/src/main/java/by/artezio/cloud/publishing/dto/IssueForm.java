package by.artezio.cloud.publishing.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Объект DTO для формы создания/редактирования и для таблицы
 * на главной странице номеров.
 * @author Igor Kuzmin
 */
public class IssueInfo {

    private Integer publishingId;

    private String publishingTitle;

    private List<Integer> deletedArticlesId;

    private List<Integer> addedArticlesId;

    private List<String> addedAdvertisingFilePath;

    private List<Integer> deletedAdvertisingId;

    private String number;

    private LocalDate localDate;

    private boolean published;

    private Integer issueId;

    /**
     * @return publishingId id публикации полученное с формы
     */
    public Integer getPublishingId() {
        return publishingId;
    }

    /**
     * @param publishingId установка id публикации номера
     */
    public void setPublishingId(final Integer publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Получение списка добавленных ссылок для рекламы.
     * @return список добавленных ссылок.
     * */
    public List<String> getAddedAdvertisingFilePath() {
        return addedAdvertisingFilePath;
    }

    /**
     * Установка ссылок добавленых через форму на странице номеров.
     * @param addedAdvertisingFilePath - список добавленных ссылок.
     * */
    public void setAddedAdvertisingFilePath(final List<String> addedAdvertisingFilePath) {
        this.addedAdvertisingFilePath = addedAdvertisingFilePath;
    }

    /**
     * Получение списка id удаленных реклам для номера.
     * @return - список id реклам удаленных из номера.
     * */
    public List<Integer> getDeletedAdvertisingId() {
        return deletedAdvertisingId;
    }

    /**
     * Установка списка id реклам которые необходимо удалить из номера.
     * @param deletedAdvertisingId - список id удаляемых реклам.
     * */
    public void setDeletedAdvertisingId(final List<Integer> deletedAdvertisingId) {
        this.deletedAdvertisingId = deletedAdvertisingId;
    }

    /**
     * Получение id статей, которые были добавлены в номер из формы.
     * @return - список статей добавленные в номер.
     * */
    public List<Integer> getAddedArticlesId() {
        return addedArticlesId;
    }

    /**
     * Установка id статей которые необходимо добавить в номер.
     * @param addedArticlesId - список id статей которые будут
     * добавлены в номер.
     * */
    public void setAddedArticlesId(final List<Integer> addedArticlesId) {
        this.addedArticlesId = addedArticlesId;
    }

    /**
     * Установка списка id статей которые будут удалены из номера.
     * @param deletedArticlesId - список id статей которые будут удалены
     * из номера.
     * */
    public void setDeletedArticlesId(final List<Integer> deletedArticlesId) {
        this.deletedArticlesId = deletedArticlesId;
    }

    /**
     * Получение списка id статей которые необходимо удалить из номера.
     * @return - список id статей которые необходимо удалитьиз номера.
     * */
    public List<Integer> getDeletedArticlesId() {
        return deletedArticlesId;
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

    /**
     * @param issueId установка идентификатора номера.
     * */
    public void setIssueId(final Integer issueId) {
        this.issueId = issueId;
    }

    /**
     * @return issueId получение идентификатора номера.
     * */
    public Integer getIssueId() {
        return issueId;
    }

}
