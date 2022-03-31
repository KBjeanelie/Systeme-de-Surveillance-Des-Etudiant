package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.administrateur.AddSubject;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Student;

public class AddStudencePresence extends AppCompatActivity {

    DatabaseAccess databaseAccess;
    Spinner spinner;
    ArrayList<String> students;
    String fullName, id_classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studence_presence);
        SplashScreenActivity.current_session.checkSession(AddStudencePresence.this);

        getClassroomFromIntent();

        databaseAccess = DatabaseAccess.getInstance(AddStudencePresence.this);
        databaseAccess.openForWritableDatabase();

        spinner = findViewById(R.id.spinner_list_student);

        students = databaseAccess.getStudentsFullNames(id_classroom);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_student_item,
                R.id.spinner_student_fullName, students);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fullName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), fullName, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getClassroomFromIntent()
    {
        if (getIntent().hasExtra("id_classroom")){
            id_classroom = getIntent().getStringExtra("id_classroom").toString();
        }
    }
}