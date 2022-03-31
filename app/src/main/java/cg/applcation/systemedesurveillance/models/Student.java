package cg.applcation.systemedesurveillance.models;

public class Student {

    private int id_student;
    private String lastname;
    private String firstname;
    private String email;
    private String tel;
    private String address;
    private String sex;

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public Student(String lastname, String firstname, String email, String tel, String address, String sex) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.sex = sex;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
