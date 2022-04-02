package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddAClass extends AppCompatActivity {

    Spinner spinner_student, spinner_subject, spinner_classroom;
    DatabaseAccess databaseAccess;
    ArrayList<String> classrooms = new ArrayList<String>();
    ArrayList<String> subjects = new ArrayList<String>();

    String label_subject, label_classroom;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_class);
        SplashScreenActivity.current_session.checkSession(AddAClass.this);

        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Ajouter un Cours");
        toolbar.inflateMenu(R.menu.nav_option_menu);

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


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (day.getText().toString().matches("") || month.getText().toString().matches("") ||
                   year.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Date vide", Toast.LENGTH_LONG).show();
                }else{
                    int d = Integer.parseInt(day.getText().toString());
                    int m = Integer.parseInt(month.getText().toString());
                    int y = Integer.parseInt(year.getText().toString());

                    if ( ((d >= 1 && d <=30) && (m == 4 || m == 6 || m == 9 || m == 11) && (y >= 2020 && y <= 2022)) ||
                            ((d >= 1 && d <=29) && (m == 2) && (y >= 2020 && y <= 2022)) ||
                            ((d >= 1 && d <=31) && (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (y >= 2020 && y <= 2022))
                    ){
                        String now = String.valueOf(d) + "/" + String.valueOf(m) + "/" + String.valueOf(y);
                         int id_subject= databaseAccess.getIdFromTableSubject(label_subject);
                         int id_classroom = databaseAccess.getIdFromTableClassroom(label_classroom);
                         int current_teacher_id = (int) SplashScreenActivity.current_session.getCurrent_teacher().getId_teacher();
                         boolean check = databaseAccess.addClass(current_teacher_id, id_subject, id_classroom, now);
                         databaseAccess.closeDatabase();
                         if (!check){
                            Toast.makeText(AddAClass.this, "Added failed", Toast.LENGTH_SHORT).show();
                         }else{
                             startActivity(new Intent(AddAClass.this, TeacherDashboard.class));
                             finish();
                         }
                    }else {
                        Toast.makeText(getApplicationContext(), "Date invalide", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

}