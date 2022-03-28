package cg.applcation.systemedesurveillance.models;

public class Classroom {

    private  long id_classroom;
    private String label_classroom;

    public Classroom(long id_classroom, String label_classroom) {
        this.id_classroom = id_classroom;
        this.label_classroom = label_classroom;
    }

    public long getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(long id_classroom) {
        this.id_classroom = id_classroom;
    }

    public String getLabel_classroom() {
        return label_classroom;
    }

    public void setLabel_classroom(String label_classroom) {
        this.label_classroom = label_classroom;
    }
}
