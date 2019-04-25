package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность реклама.
 * @author Igor Kuzmin.
 * */
public class Advertising {

    private Integer id;
    private Integer issueId;
    private String filePath;

    /**
     * Конструктор с параметрами.
     * @param issueId - id {@link Issue} для которого создается реклама.
     * @param filePath - ссылочные пути для реклам.
     * */
    public Advertising(final Integer issueId, final String filePath) {
        this.issueId = issueId;
        this.filePath = filePath;
    }

    /**
     * Конструктор без параметров.
     * */
    public Advertising() {

    }

    /**
     * Метод для установки id рекламы.
     * @param id - идентификатор рекламы
     * */
    public void setId(final Integer id) {
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
    public void setIssueId(final Integer issueId) {
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
