package by.artezio.cloud.publishing.dto;

/**
 * Объект DTO содержащий результат выполнения
 * опреаций удаления/вставки/редактирования.
 * На основе полей данного объекта формируется
 * сообщение в информационном модальном окне.
 *
 * @author Igor Kuzmin
 * */
public class IssueOperationResult {

    private String status;

    private Integer issueId;

    private String publishingTitle;

    private String number;

    /**
     * Метод получения id номера, над которым производилась операция.
     * @return - id номера.
     * */
    public Integer getIssueId() {
        return issueId;
    }

    /**
     * Метод установки id номера, над которым производилась операция.
     * @param issueId - id номера.
     * */
    public void setIssueId(final Integer issueId) {
        this.issueId = issueId;
    }

    /**
     * Метод получение номера, над которым производилась операция.
     * @return - значение номера.
     * */
    public String getNumber() {
        return number;
    }

    /**
     * Метод установки номера, над которым производилась операция.
     * @param number - значение номера.
     * */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Метод получение названия издания, над которым производилась операция.
     * @return - название издания.
     * */
    public String getPublishingTitle() {
        return publishingTitle;
    }

    /**
     * Метод установки названия издания, над которым производилась операция.
     * @param publishingTitle - название издания.
     * */
    public void setPublishingTitle(final String publishingTitle) {
        this.publishingTitle = publishingTitle;
    }

    /**
     * Метод установки статуса выполнения операции.
     * @return - статус выполнения операции.
     * */
    public String getStatus() {
        return status;
    }

    /**
     * Метод получения статуса выполнения операции.
     * @param status - статус выполнения операции.
     * */
    public void setStatus(final String status) {
        this.status = status;
    }
}
