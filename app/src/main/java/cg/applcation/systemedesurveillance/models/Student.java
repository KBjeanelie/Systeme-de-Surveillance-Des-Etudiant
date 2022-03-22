package cg.applcation.systemedesurveillance.models;

public class Student {

    private String lastname;
    private String firstname;
    private String email;
    private String tel;
    private String address;
    private String level ;
    private String option;
    private String sex;


    public Student(String lastname, String firstname, String email, String tel, String address, String level, String option, String sex) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.level = level;
        this.option = option;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}