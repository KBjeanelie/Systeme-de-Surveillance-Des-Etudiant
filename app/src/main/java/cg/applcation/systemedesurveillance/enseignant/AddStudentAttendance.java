package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddStudentAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_attendance);

        findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStudentAttendance.this, TeacherDashboard.class));
                finish();
            }
        });
    }

    /*** public void storeDataInList(){
        Cursor cursor = myDB.readAllDataInTableClassroom();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data store in classroom", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Ready to store data", Toast.LENGTH_LONG).show();
        }
    }
     ***/
}