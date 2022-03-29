package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.customadapter.CustomeTeacherAdapter;
import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;

public class DisplayTeachers extends AppCompatActivity {

    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    ArrayList<Teacher> teachers;
    CustomeTeacherAdapter customeTeacherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_teachers);

        recyclerView = findViewById(R.id.recyclerview_display_teacher);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        teachers = new ArrayList<Teacher>();
        displayData();

        customeTeacherAdapter = new CustomeTeacherAdapter(DisplayTeachers.this, teachers);
        recyclerView.setAdapter(customeTeacherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayTeachers.this));

    }

    public void displayData(){
        Cursor cursor = databaseAccess.readAllDataInTableTeacher();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "data stored :)", Toast.LENGTH_LONG).show();

            while (cursor.moveToNext()){
                Teacher teacher = new Teacher(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8),"");
                teacher.setId_teacher(Integer.parseInt(cursor.getString(0)));

                teachers.add(teacher);
            }
        }
    }
}