package by.artezio.cloud.publishing.domain;

public class Publishing {

    private int id;
    private String title;
    private char type;
    private String subjects;

    public Publishing() {
    }

    public Publishing(int id, String title, char type, String subjects) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
