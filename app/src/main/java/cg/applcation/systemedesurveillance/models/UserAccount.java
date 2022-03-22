package cg.applcation.systemedesurveillance.models;

public class UserAccount {

    private String email;
    private String teacher_tel;
    private String password;

    public UserAccount(String email, String teacher_tel, String password) {
        this.email = email;
        this.teacher_tel = teacher_tel;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacher_tel() {
        return teacher_tel;
    }

    public void setTeacher_tel(String teacher_tel) {
        this.teacher_tel = teacher_tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
