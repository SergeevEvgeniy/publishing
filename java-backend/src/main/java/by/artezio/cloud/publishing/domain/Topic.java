package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность, представляет запись из таблицы "topic".
 */
public class Topic {

    private Integer id;
    private String name;

    /**
     * Пустой конструктор для создания рубрики.
     */
    public Topic() {
    }

    /**
     * Конструктор с параметрами для создания рубрики.
     *
     * @param id   идентификатор рубрики
     * @param name название рубрики
     */
    public Topic(final Integer id, final String name) {
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
