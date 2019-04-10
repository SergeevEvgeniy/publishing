package by.artezio.cloud.publishing.domain;

/**
 * Класс-сущность для таблицы publishing-employee.
 * */
public class PublishingEmployee {

    private int employeeId;

    private int publishingId;

    /**
     * Конструктор с параметрами.
     * @param publishingId - id {@link Publishing}.
     * @param employeeId - id {@link Employee}.
     * */
    public PublishingEmployee(final int publishingId, final int employeeId) {
        this.publishingId = publishingId;
        this.employeeId = employeeId;
    }

    /**
     * Метод установки id журнала/газеты.
     * @param publishingId - id {@link Publishing}
     * */
    public void setPublishingId(final int publishingId) {
        this.publishingId = publishingId;
    }

    /**
     * Метод получения id журнала/газеты.
     * @return - id {@link Publishing}
     * */
    public int getPublishingId() {
        return publishingId;
    }

    /**
     * Метод установки id сотрудника.
     * @param employeeId - id {@link Employee}
     * */
    public void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Метод получения id сотрудника.
     * @return - id {@link Employee}
     * */
    public int getEmployeeId() {
        return employeeId;
    }

}
