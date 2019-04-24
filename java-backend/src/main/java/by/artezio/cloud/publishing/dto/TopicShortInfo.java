package by.artezio.cloud.publishing.dto;

/**
 * @author Denis Shubin
 */
public class TopicShortInfo {

    private int id;
    private String name;

    /**
     * @return id рубрики
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id рубрики
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return название рубрики
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название рубрики
     */
    public void setName(final String name) {
        this.name = name;
    }
}
