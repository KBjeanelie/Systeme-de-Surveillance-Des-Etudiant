package cg.applcation.systemedesurveillance.authentification;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;
import cg.applcation.systemedesurveillance.models.UserAccount;

public  class Session {

    private  boolean isAuth = false;
    private Teacher current_teacher;
    private UserAccount current_user;
    private final ArrayList<Classes> classes = new ArrayList<Classes>();

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


    public void getAllClasses(Context context, DatabaseAccess databaseAccess)
    {
        Cursor cursor = databaseAccess.readAllDataInTableClasses();

        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                Classes single_classes = new Classes(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),
                        cursor.getString(4));

                classes.add(single_classes);
            }
        }
    }

    public ArrayList<Classes> getAllClassesOfSingleTeacher(int id_teacher)
    {
        ArrayList<Classes> cl = new ArrayList<Classes>();

        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getId_teacher() == id_teacher){
                cl.add(classes.get(i));
            }
        }
        return cl;
    }
}
