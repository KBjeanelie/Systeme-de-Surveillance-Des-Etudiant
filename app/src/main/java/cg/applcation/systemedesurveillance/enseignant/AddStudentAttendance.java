package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddStudentAttendance extends AppCompatActivity {

    Spinner spinner_student, spinner_subject, spinner_classroom;
    DatabaseAccess databaseAccess;
    ArrayList<String> classrooms = new ArrayList<String>();
    ArrayList<String> subjects = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_attendance);

        spinner_classroom = findViewById(R.id.ads_id_classroom);
        spinner_subject = findViewById(R.id.ads_id_subject);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        classrooms = databaseAccess.getAllDataInClassroom();
        subjects = databaseAccess.getAllDataInSubject();

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this, R.layout.spinner_item_classroom, R.id.label_classroom, classrooms);
        spinner_classroom.setAdapter(adapter_class);

        ArrayAdapter<String> adapter_sub = new ArrayAdapter<String>(this, R.layout.spinner_item_subject, R.id.label_subject, subjects);
        spinner_subject.setAdapter(adapter_sub);

        findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStudentAttendance.this, TeacherDashboard.class));
                finish();
            }
        });
    }

}