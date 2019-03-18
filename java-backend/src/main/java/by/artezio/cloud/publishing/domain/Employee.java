package by.artezio.cloud.publishing.domain;

import java.util.Objects;

/**
 * Класс-сущность, представляет строку из таблицы "Employee".
 *
 * @author Denis Shubin / Sergeev Evgeniy
 */
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private Character sex;
    private Integer birthYear;
    private String address;
    private Character type;
    private Integer educationId;
    private Boolean chiefEditor;

    /**
     * Конструктор по умолчанию.
     */
    public Employee() {
    }

    /**
     * Возвращает id сотрудника.
     *
     * @return int
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает id сотрудника.
     *
     * @param id int
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Устанавливает имя сотрудника.
     *
     * @param firstName String
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Возвращает фамилию сотрудника.
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Устанавливает фамилию сотрудника.
     *
     * @param lastName String
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Возвращает отчество сотрудника.
     *
     * @return String
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Устанавливает отчество сотрудника.
     *
     * @param middleName String
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * Возвращает email сотрудника.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает email сотрудника.
     *
     * @param email String
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Возвращает пароль сотрудника.
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль сотрудника.
     *
     * @param password String
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Возвращает пол сотрудника.
     *
     * @return String
     */
    public Character getSex() {
        return sex;
    }

    /**
     * Устанавливает пол сотрудника.
     *
     * @param sex String
     */
    public void setSex(final Character sex) {
        this.sex = sex;
    }

    /**
     * Возвращает год рождения сотрудника.
     *
     * @return int
     */
    public Integer getBirthYear() {
        return birthYear;
    }

    /**
     * Устанавливает год рождения сотрудника.
     *
     * @param birthYear int
     */
    public void setBirthYear(final Integer birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Возвращает адрес сотрудника.
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Устанавливает адрес сотрудника.
     *
     * @param address String
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Возвращает тип сотрудника.
     *
     * @return String
     */
    public Character getType() {
        return type;
    }

    /**
     * Устанавливает тип сотрудника.
     *
     * @param type String
     */
    public void setType(final Character type) {
        this.type = type;
    }

    /**
     * Возвращает id образования сотрудника.
     *
     * @return int
     */
    public Integer getEducationId() {
        return educationId;
    }

    /**
     * Устанавливает id образования сотрудника.
     *
     * @param educationId int
     */
    public void setEducationId(final Integer educationId) {
        this.educationId = educationId;
    }

    /**
     * Возвращает true, если сотрудник является главным редактором, иначе false.
     *
     * @return boolean
     */
    public Boolean isChiefEditor() {
        return chiefEditor;
    }

    /**
     * Устанавливает признак того, является ли сотрудник главным редактором.
     *
     * @param chiefEditor boolean
     */
    public void setChiefEditor(final Boolean chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
