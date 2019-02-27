package by.artezio.cloud.publishing.domain;

import java.util.Objects;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String sex;
    private int birthYear;
    private String address;
    private String type;
    private String educationId;
    private boolean chiefEditor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public boolean isChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(boolean chiefEditor) {
        this.chiefEditor = chiefEditor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
            birthYear == employee.birthYear &&
            chiefEditor == employee.chiefEditor &&
            Objects.equals(firstName, employee.firstName) &&
            Objects.equals(lastName, employee.lastName) &&
            Objects.equals(middleName, employee.middleName) &&
            Objects.equals(email, employee.email) &&
            Objects.equals(password, employee.password) &&
            Objects.equals(sex, employee.sex) &&
            Objects.equals(address, employee.address) &&
            Objects.equals(type, employee.type) &&
            Objects.equals(educationId, employee.educationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, email, password, sex, birthYear, address, type, educationId, chiefEditor);
    }
}
