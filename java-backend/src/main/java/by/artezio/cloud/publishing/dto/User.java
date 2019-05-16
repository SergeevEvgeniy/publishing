package by.artezio.cloud.publishing.dto;

import by.artezio.cloud.publishing.domain.Employee;

/**
 * Класс-сущность dto для получения краткой информации о залогиненном
 * пользователе.
 *
 * @author Sergeev Evgeniy
 */
public class User {

    private final int id;
    private final String name;
    private final Character type;
    private final boolean isChiefEditor;
    private final boolean isJournalist;

    /**
     *
     * @param employee Employee
     */
    public User(final Employee employee) {
        id = employee.getId();
        name = employee.getFirstName() + " " + employee.getLastName();
        type = employee.getType();
        isChiefEditor = employee.isChiefEditor();
        isJournalist = employee.getType().equals('J');
    }

    /**
     *
     * @return String имя + фамилия
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Character тип роли пользователя
     */
    public Character getType() {
        return type;
    }

    /**
     *
     * @return Boolean является ли пользователь главным редактором
     */
    public boolean isChiefEditor() {
        return isChiefEditor;
    }

    /**
     *
     * @return id пользователя
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return Boolean является ли пользователь журналистом
     */
    public boolean isJournalist() {
        return isJournalist;
    }
}
