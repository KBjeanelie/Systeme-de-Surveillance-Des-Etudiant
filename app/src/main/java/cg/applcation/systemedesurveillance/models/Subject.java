package cg.applcation.systemedesurveillance.models;

public class Subject {

    private String label;
    private long id_subject;

    public Subject(String label, long id_subject) {
        this.label = label;
        this.id_subject = id_subject;
    }

    public long getId_subject() {
        return id_subject;
    }

    public void setId_subject(long id_subject) {
        this.id_subject = id_subject;
    }

    public Subject(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
