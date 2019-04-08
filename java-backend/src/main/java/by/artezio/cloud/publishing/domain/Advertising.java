package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность реклама.
 * */
public class Advertising {

    private int id;
    private int issueId;
    private String filePath;

    /**
     * Метод для установки id рекламы.
     * @param id - идентификатор рекламы
     * */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Метод для получения id рекламы.
     * @return id - идентификатор рекламы
     * */
    public int getId() {
        return id;
    }

    /**
     * Метод для установки id номера.
     * @param issueId - идентификатор номера
     * */
    public void setIssueId(final int issueId) {
        this.issueId = issueId;
    }

    /**
     * Метод для получения id номера.
     * @return issueId - идентификатор номера
     * */
    public int getIssueId() {
        return issueId;
    }

    /**
     * Метод для установки месторасположение рекламы.
     * @param filePath - месторасположение рекламы
     * */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * Метод для получения месторасположение рекламы.
     * @return filePath - месторасположение рекламы
     * */
    public String getFilePath() {
        return filePath;
    }

}
