package by.artezio.cloud.publishing.dto;

/**
 * @author Denis Shubin
 */
public class EmployeeShortInfo {

    private int id;
    private String shortFullName;

    /**
     * @return id сотрудника
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id сотрудника
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return сокращённое полное имя сотрудника
     */
    public String getShortFullName() {
        return shortFullName;
    }

    /**
     * @param shortFullName сокращённое полное имя сотрудника
     */
    public void setShortFullName(final String shortFullName) {
        this.shortFullName = shortFullName;
    }
}
