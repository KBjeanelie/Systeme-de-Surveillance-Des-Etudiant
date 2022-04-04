package cg.applcation.systemedesurveillance.enseignant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.AboutFragment;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.customadapter.CustomesClassesAdapter;
import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Presence;
import cg.applcation.systemedesurveillance.models.Subject;

public class TeacherDashboard extends AppCompatActivity {

    DatabaseAccess databaseAccess;

    RecyclerView recyclerView;

    CustomesClassesAdapter customesClassesAdapter;

    private Toolbar toolbar;

    ArrayList<Classes> classes;

    ArrayList<Classroom> classrooms;

    ArrayList<Subject> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();


        recyclerView = findViewById(R.id.recyclerview_display_classes);
        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Dashboard");
        toolbar.inflateMenu(R.menu.nav_option_menu);
        setSupportActionBar(toolbar);


        classes = new ArrayList<Classes>();
        storeDataInClasses();

        classrooms = new ArrayList<Classroom>();
        storeDataClassroom();

        subjects = new ArrayList<Subject>();
        storeDataSubject();

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

    public void storeDataInClasses(){
        Cursor cursor = databaseAccess.readAllDataInTableClasses();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                if (cursor.getInt(1) == SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher()){
                    Classes single_classes = new Classes(cursor.getInt(0), cursor.getInt(1),
                            cursor.getInt(2), cursor.getInt(3), cursor.getString(4));

                    classes.add(single_classes);
                }
            }
        }
    }

    public void storeDataSubject(){
        Cursor cursor = databaseAccess.readAllDataInTableSubject();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Subject subject = new Subject(cursor.getString(1), cursor.getLong(0));
                subjects.add(subject);
            }
        }
    }

    public void storeDataClassroom(){
        Cursor cursor = databaseAccess.readAllDataInTableClassroom();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Classroom classroom = new Classroom(cursor.getLong(0), cursor.getString(1));
                classrooms.add(classroom);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_option_menu, menu); //your file name
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), AboutApp.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                return true;
            case R.id.logout:
                SplashScreenActivity.current_session.logout();
                SplashScreenActivity.ADMIN_SESSION = false;
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}