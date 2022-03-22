package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.R;

public class TeacherDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        findViewById(R.id.card_display_all_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherDashboard.this, DisplayStudentsAttendance.class));
                finish();
            }
        });

        findViewById(R.id.card_add_student_attendance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherDashboard.this, AddStudentAttendance.class));
                finish();
            }
        });

        findViewById(R.id.ic_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}