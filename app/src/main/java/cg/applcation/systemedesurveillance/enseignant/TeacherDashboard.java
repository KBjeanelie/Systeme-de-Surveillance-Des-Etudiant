package cg.applcation.systemedesurveillance.enseignant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.customadapter.CustomesClassesAdapter;
import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Subject;

public class TeacherDashboard extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    CustomesClassesAdapter customesClassesAdapter;

    ArrayList<Classes> classes;
    ArrayList<Classroom> classrooms;
    ArrayList<Subject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        SplashScreenActivity.current_session.checkSession(TeacherDashboard.this);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();
        classes = SplashScreenActivity.current_session.getAllClassesOfSingleTeacher(
                (int) SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher());


        recyclerView = findViewById(R.id.recyclerview_display_classes);


        classrooms = getClassrooms(TeacherDashboard.this, databaseAccess);
        subjects = getSubject(TeacherDashboard.this, databaseAccess);

        classes = SplashScreenActivity.current_session.getAllClassesOfSingleTeacher(
                (int) SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher());



        customesClassesAdapter = new CustomesClassesAdapter(TeacherDashboard.this, classes, classrooms, subjects);

        recyclerView.setAdapter(customesClassesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherDashboard.this));
        findViewById(R.id.add_classes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherDashboard.this, AddAClass.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                //your code
                // EX : call intent if you want to swich to other activity
                return true;
            case R.id.logout:
                SplashScreenActivity.current_session.setAuth(false);
                SplashScreenActivity.current_session.logout();
                startActivity(new Intent(TeacherDashboard.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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


    public void update()
    {
        classes = SplashScreenActivity.current_session.getAllClassesOfSingleTeacher(
                (int) SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher());
    }
}