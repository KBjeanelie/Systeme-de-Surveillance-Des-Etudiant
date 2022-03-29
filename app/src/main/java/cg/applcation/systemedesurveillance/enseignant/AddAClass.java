package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddAClass extends AppCompatActivity {

    Spinner spinner_student, spinner_subject, spinner_classroom;
    DatabaseAccess databaseAccess;
    ArrayList<String> classrooms = new ArrayList<String>();
    ArrayList<String> subjects = new ArrayList<String>();

    DatePickerDialog.OnDateSetListener setListener;

    String label_subject, label_classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_class);

        spinner_classroom = findViewById(R.id.ads_id_classroom);
        spinner_subject = findViewById(R.id.ads_id_subject);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        classrooms = databaseAccess.getAllDataInTableClassroom();
        subjects = databaseAccess.getAllDataInTableSubject();

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this, R.layout.spinner_item_classroom, R.id.label_classroom, classrooms);
        spinner_classroom.setAdapter(adapter_class);

        ArrayAdapter<String> adapter_sub = new ArrayAdapter<String>(this, R.layout.spinner_item_subject, R.id.label_subject, subjects);
        spinner_subject.setAdapter(adapter_sub);

        spinner_classroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                label_classroom = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                label_subject = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        EditText day = findViewById(R.id.day);
        EditText month = findViewById(R.id.month);
        EditText year = findViewById(R.id.year);

        String now = year.getText().toString() + "-" + month.getText().toString() + "-" + day.getText().toString();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_subject= databaseAccess.getIdFromTableSubject(label_subject);
                int id_classroom = databaseAccess.getIdFromTableClassroom(label_classroom);
                int current_teacher_id = (int) SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher();
                boolean check = databaseAccess.addClass(current_teacher_id, id_subject, id_classroom, now);

                if (!check){
                    Toast.makeText(AddAClass.this, "Added failed", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(AddAClass.this, "Added SuccessFully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}