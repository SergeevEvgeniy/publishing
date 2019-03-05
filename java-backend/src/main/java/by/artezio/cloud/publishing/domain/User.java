package by.artezio.cloud.publishing.domain;

public class User {

    private final int id;
    private final String first_name;
    private final String last_name;
    private final String middle_name;
    private final String email;
    private final String password;
    private final char sex;
    private final int birth_year;
    private final String address;
    private final char type;
    private final int education_id;
    private final int chief_editor;

    public User(int id, String first_name, String last_name, String middle_name, String email, String password, char sex, int birth_year, String address, char type, int education_id, int chief_editor) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.birth_year = birth_year;
        this.address = address;
        this.type = type;
        this.education_id = education_id;
        this.chief_editor = chief_editor;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public String getEmail() {
        return email;
    }

}
