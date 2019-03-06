package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляет запись из таблицы "topic".
 */
public class Topic {

    private Integer id;
    private String name;

    /**
     * Конструктор, создаёт класс со стандартными значениями полей.
     */
    public Topic() {
    }

    /**
     * Конструктор для создания объекта с указанными значениями полей.
     * @param id - id рубрики
     * @param name - название рубрики
     */
    public Topic(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Получение идентификатора рубрики.
     *
     * @return идентификатор рубрики
     */
    public Integer getId() {
        return id;
    }

    /**
     * Установка идентификатора рубрики.
     *
     * @param id идентификатор рубрики
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Получение названия рубрики.
     *
     * @return название рубрики
     */
    public String getName() {
        return name;
    }

    /**
     * Установка названия рубрики.
     *
     * @param name название рубрики
     */
    public void setName(final String name) {
        this.name = name;
    }
}
