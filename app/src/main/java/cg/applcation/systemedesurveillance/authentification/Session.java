package cg.applcation.systemedesurveillance.authentification;

import cg.applcation.systemedesurveillance.models.Teacher;
import cg.applcation.systemedesurveillance.models.UserAccount;

public  class Session {

    private  boolean isAuth = false;
    private Teacher current_teacher;
    private UserAccount current_user;

    public Session(boolean isAuth, Teacher current_teacher, UserAccount current_user) {
        this.isAuth = isAuth;
        this.current_teacher = current_teacher;
        this.current_user = current_user;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public Teacher getCurrent_teacher() {
        return current_teacher;
    }

    public void setCurrent_teacher(Teacher current_teacher) {
        this.current_teacher = current_teacher;
    }

    public UserAccount getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(UserAccount current_user) {
        this.current_user = current_user;
    }
}
