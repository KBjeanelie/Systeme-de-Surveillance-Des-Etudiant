package cg.applcation.systemedesurveillance.authentification;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Presence;
import cg.applcation.systemedesurveillance.models.Student;
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

    public void logout(){
        isAuth = false;
        current_teacher = null;
        current_user = null;
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
