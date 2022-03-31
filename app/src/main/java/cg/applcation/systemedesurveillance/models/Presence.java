package cg.applcation.systemedesurveillance.models;

public class Presence {
    private int id_classes;
    private int id_student;

    public Presence(int id_classes, int id_student) {
        this.id_classes = id_classes;
        this.id_student = id_student;
    }

    public int getId_classes() {
        return id_classes;
    }

    public void setId_classes(int id_classes) {
        this.id_classes = id_classes;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }
}
