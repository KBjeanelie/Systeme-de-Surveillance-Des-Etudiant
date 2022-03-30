package cg.applcation.systemedesurveillance.models;

public class Classes {

    private int id_classes;
    private int id_teacher;
    private int id_subject;
    private int id_classroom;
    private String date_of_classes;

    public Classes(int id_classes, int id_teacher, int id_subject, int id_classroom, String date_of_classes) {
        this.id_classes = id_classes;
        this.id_teacher = id_teacher;
        this.id_subject = id_subject;
        this.id_classroom = id_classroom;
        this.date_of_classes = date_of_classes;
    }

    public int getId_classes() {
        return id_classes;
    }

    public void setId_classes(int id_classes) {
        this.id_classes = id_classes;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public int getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(int id_classroom) {
        this.id_classroom = id_classroom;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getDate_of_classes() {
        return date_of_classes;
    }

    public void setDate_of_classes(String date_of_classes) {
        this.date_of_classes = date_of_classes;
    }
}
