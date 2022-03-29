package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.customadapter.CustomesClassesAdapter;
import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Subject;
import cg.applcation.systemedesurveillance.models.Teacher;

public class DisplayAllClasses extends AppCompatActivity {

    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    CustomesClassesAdapter customesClassesAdapter;

    ArrayList<Classes> classes = new ArrayList<Classes>();
    ArrayList<Classroom> classrooms;
    ArrayList<Subject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_classes);

        recyclerView = findViewById(R.id.recyclerview_display_classes);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        classrooms = getClassrooms(DisplayAllClasses.this, databaseAccess);
        subjects = getSubject(DisplayAllClasses.this, databaseAccess);

        storeData();


        customesClassesAdapter = new CustomesClassesAdapter(DisplayAllClasses.this, classes, classrooms, subjects);

        recyclerView.setAdapter(customesClassesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayAllClasses.this));
    }

    public void storeData(){
        Cursor cursor = databaseAccess.readAllDataInTableClasses();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                Classes single_classes = new Classes(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),
                        cursor.getString(4));

                classes.add(single_classes);
            }
        }
    }

    public static ArrayList<Classroom> getClassrooms(Context context, DatabaseAccess db){
        Cursor cursor = db.readAllDataInTableClassroom();

        ArrayList<Classroom> classrooms  = new ArrayList<Classroom>();

        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                Classroom classroom = new Classroom(cursor.getLong(0), cursor.getString(1));

                classrooms.add(classroom);
            }
            return classrooms;
        }
        return classrooms;
    }

    public static ArrayList<Subject> getSubject(Context context, DatabaseAccess db){
        Cursor cursor = db.readAllDataInTableSubject();

        ArrayList<Subject> subjects = new ArrayList<Subject>();

        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                Subject subject = new Subject(cursor.getString(1), cursor.getLong(0));

                subjects.add(subject);
            }
            return subjects;
        }
        return subjects;
    }


}