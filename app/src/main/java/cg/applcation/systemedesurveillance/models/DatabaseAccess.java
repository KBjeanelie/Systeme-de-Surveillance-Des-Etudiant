package cg.applcation.systemedesurveillance.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseAccess {

    private final SQLiteOpenHelper sqLiteOpenHelper;
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

        return result != -1;
    }

    public boolean addStudent(Student student, int id_classroom){
        ContentValues contentValues = new ContentValues();

        contentValues.put("student_lastname", student.getLastname());
        contentValues.put("student_firstname", student.getFirstname());
        contentValues.put("student_email", student.getEmail());
        contentValues.put("student_phone_number", student.getTel());
        contentValues.put("student_address", student.getAddress());
        contentValues.put("student_sex", student.getSex());
        contentValues.put("id_classroom", id_classroom);

        long result = database.insert("Student", null, contentValues);

        return result != -1;
    }

    public boolean addAccountUser(String username, String password, long teacher_id){
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("id_teacher", teacher_id);

        long result = database.insert("Account", null, contentValues);

        return result != -1;
    }

    public boolean addClass(int id_teacher, int id_subject, int id_classroom, String date_of_classes) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_teacher", id_teacher);
        contentValues.put("id_subject", id_subject);
        contentValues.put("id_classroom", id_classroom);
        contentValues.put("date_of_classes", date_of_classes);

        long latestIdClasses = database.insert("Classes", null, contentValues);
        return latestIdClasses != -1;
    }

    public boolean addNewClassroom(String label_classroom){
        ContentValues contentValues = new ContentValues();

        contentValues.put("label_classroom", label_classroom);
        long result = database.insert("Classroom", null, contentValues);

        return result != -1;
    }

    public boolean addNewSubject(String label_subject){
        ContentValues contentValues = new ContentValues();

        contentValues.put("label_subject", label_subject);
        long result = database.insert("Subject", null, contentValues);

        return result != -1;
    }

    public boolean addPresence(int id_classes, int id_student){
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_classes", id_classes);
        contentValues.put("id_student", id_student);

        long result = database.insert("Presence", null, contentValues);
        return result != -1;
    }



    private Cursor __readAllDataInTableClassroom(){
        String query = "SELECT label_classroom FROM Classroom;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    private Cursor __readAllDataInTableSubject(){
        String query = "SELECT label_subject FROM Subject;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableSubject(){
        String query = "SELECT * FROM Subject;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableClassroom(){
        String query = "SELECT * FROM Classroom;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableStudent(){
        String query = "SELECT * FROM Student;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableTeacher(){
        String query = "SELECT * FROM Teacher;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableClasses(){
        String query = "SELECT * FROM Classes;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTablePresence(){
        String query = "SELECT * FROM Presence;";

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataInTableAccount(){
        String query = "SELECT * FROM Account;";

        if (database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }



    public int getIdFromTableTeacher(String email){
        if (database != null){
            cursor = database.query("Teacher", new String[]{"id_teacher"}, "teacher_email=?",
                    new String[]{email}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public ArrayList<String> getAllDataInTableClassroom(){
        cursor = __readAllDataInTableClassroom();
        ArrayList<String> Classrooms = new ArrayList<String>();
        while (cursor.moveToNext()){
            Classrooms.add(cursor.getString(0));
        }
        return Classrooms;
    }

    public ArrayList<String> getAllDataInTableSubject(){
        cursor = __readAllDataInTableSubject();
        ArrayList<String> Subjects = new ArrayList<String>();
        while (cursor.moveToNext()){
            Subjects.add(cursor.getString(0));
        }
        return Subjects;
    }

    public int getIdFromTableClassroom(String label_classroom){
        if (database != null){
            cursor = database.query("Classroom", new String[]{"id_classroom"}, "label_classroom=?",
                    new String[]{label_classroom}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public int getIdFromTableSubject(String label_subject){
        if (database != null){
            cursor = database.query("Subject", new String[]{"id_subject"}, "label_subject=?",
                    new String[]{label_subject}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public String getSingleLabelFromClassroom(String id_classroom)
    {
        if (database != null){
            cursor = database.query("Classroom", new String[]{"label_classroom"}, "id_classroom=?",
                    new String[]{id_classroom}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getString(0);
    }

    public String getSingleLabelFromSubject(String id_subject)
    {
        if (database != null){
            cursor = database.query("Subject", new String[]{"label_subject"}, "id_subject=?",
                    new String[]{id_subject}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getString(0);
    }

    public String getFullNameFromTeacher(String id_teacher)
    {
        if (database != null){
            cursor = database.query("Teacher", new String[]{"teacher_lastname, teacher_firstname"}, "id_teacher=?",
                    new String[]{id_teacher}, null, null, null);
        }
        cursor.moveToNext();
        return cursor.getString(0) + " " + cursor.getString(1);
    }

    public ArrayList<Student> getStudentsFullNames(String id_classroom)
    {
        String query = "SELECT * FROM Student WHERE id_classroom = " + id_classroom;
        ArrayList<Student> students = new ArrayList<Student>();

        if(database != null){
            cursor = database.rawQuery(query, null);
        }

        while (cursor.moveToNext()){
            Student student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getInt(7));

            students.add(student);
        }
        return students;
    }





}
