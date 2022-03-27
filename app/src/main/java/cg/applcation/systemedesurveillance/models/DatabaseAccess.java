package cg.applcation.systemedesurveillance.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess  newInstance;
    Cursor cursor = null;

    public DatabaseAccess(Context context) {
        this.sqLiteOpenHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (newInstance== null){
            newInstance = new DatabaseAccess(context);
        }
        return  newInstance;
    }

    public void openForWritableDatabase(){
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void openForReadableDatabase(){
        database = sqLiteOpenHelper.getReadableDatabase();
    }

    public void closeDatabase(){
        if (database != null){
            database.close();
        }
    }

    public boolean addTeacher(Teacher teacher){
        ContentValues contentValues = new ContentValues();

        contentValues.put("teacher_lastname", teacher.getLastname());
        contentValues.put("teacher_firstname", teacher.getFirstname());
        contentValues.put("teacher_email", teacher.getEmail());
        contentValues.put("teacher_job_function", teacher.getJob_function());
        contentValues.put("teacher_work_at", teacher.getWork_at());
        contentValues.put("teacher_phone_number", teacher.getTel());
        contentValues.put("teacher_address", teacher.getAddress());
        contentValues.put("teacher_sex", teacher.getSex());

        long result = database.insert("Teacher", null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public void addStudent(Context context, Student student, int id_classroom){
        ContentValues contentValues = new ContentValues();

        contentValues.put("student_lastname", student.getLastname());
        contentValues.put("student_firstname", student.getFirstname());
        contentValues.put("student_email", student.getEmail());
        contentValues.put("student_phone_number", student.getTel());
        contentValues.put("student_address", student.getAddress());
        contentValues.put("student_sex", student.getSex());
        contentValues.put("id_classroom", id_classroom);

        long result = database.insert("Student", null, contentValues);

        if(result == -1){
            Toast.makeText(context, "Failed adding Student in database :(", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Added successfully :)", Toast.LENGTH_LONG).show();
        }
    }

    public void addAccountUser(Context context, String username, String password, int teacher_id){
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("id_teacher", teacher_id);

        long result = database.insert("Account", null, contentValues);

        if(result == -1){
            Toast.makeText(context, "Failed adding Account in database :(", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Added successfully :)", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor readAllDataInTableClassroom(){
        String query = "SELECT * FROM Classroom;";
        openForReadableDatabase();

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableTeacher(){
        String query = "SELECT * FROM Teacher;";
        openForReadableDatabase();

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

}
