package cg.applcation.systemedesurveillance.models;

public class Teacher {

    private String lastname;
    private String firstname;
    private String email;
    private String job_function;
    private String work_at;
    private String tel;
    private String address;
    private String teach_in;
    private String subject;
    private String sex;
    private String created_at;

    public Teacher(String lastname, String firstname, String email, String job_function, String work_at, String tel, String address, String teach_in, String subject, String sex, String created_at) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.job_function = job_function;
        this.work_at = work_at;
        this.tel = tel;
        this.address = address;
        this.teach_in = teach_in;
        this.subject = subject;
        this.sex = sex;
        this.created_at = created_at;
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

    public String getJob_function() {
        return job_function;
    }

    public void setJob_function(String job_function) {
        this.job_function = job_function;
    }

    public String getWork_at() {
        return work_at;
    }

    public void setWork_at(String work_at) {
        this.work_at = work_at;
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

    public String getTeach_in() {
        return teach_in;
    }

    public void setTeach_in(String teach_in) {
        this.teach_in = teach_in;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
