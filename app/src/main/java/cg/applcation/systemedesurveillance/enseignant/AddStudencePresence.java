package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Presence;
import cg.applcation.systemedesurveillance.models.Student;

public class AddStudencePresence extends AppCompatActivity {


    DatabaseAccess databaseAccess;

    Spinner spinner;

    ArrayList<Student> students;

    ArrayList<Presence> presences;

    Student get_student;

    String id_classes, id_teacher, id_subject, id_classroom, d_cl;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_studence_presence);
        SplashScreenActivity.current_session.checkSession(AddStudencePresence.this);

        getClassroomFromIntent();

        databaseAccess = DatabaseAccess.getInstance(AddStudencePresence.this);
        databaseAccess.openForWritableDatabase();

        presences = new ArrayList<Presence>();
        storeDataInPresences();

        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Ajouter Presence");
        spinner = findViewById(R.id.spinner_list_student);

        students = databaseAccess.getStudentsFullNames(id_classroom);

        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this, R.layout.spinner_student_item,
                R.id.spinner_student_fullName, students);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                get_student = (Student) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = databaseAccess.addPresence(Integer.parseInt(id_classes), get_student.getId_student());
                Intent intent = new Intent(AddStudencePresence.this, ShowPresence.class);
                intent.putExtra("id_classroom", id_classroom);
                intent.putExtra("id_classes", id_classes);
                intent.putExtra("id_subject", id_subject);
                intent.putExtra("id_teacher", id_teacher);
                intent.putExtra("date_of_classes", d_cl);
                startActivity(intent);
                finish();
                if (check){
                    Toast.makeText(getApplicationContext(), "Added Successfully :)", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "No database found :(", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void getClassroomFromIntent()
    {
        if (getIntent().hasExtra("id_classes") && getIntent().hasExtra("id_teacher") && getIntent().hasExtra("id_subject") &&
                getIntent().hasExtra("id_classroom") && getIntent().hasExtra("date_of_classes"))
        {
            id_classes = getIntent().getStringExtra("id_classes");
            id_teacher = getIntent().getStringExtra("id_teacher");
            id_subject = getIntent().getStringExtra("id_subject");
            id_classroom = getIntent().getStringExtra("id_classroom");
            d_cl = getIntent().getStringExtra("date_of_classes");
        }
    }

    public void storeDataInPresences(){
        Cursor cursor = databaseAccess.readAllDataInTablePresence();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Presence presence = new Presence(cursor.getInt(0), cursor.getInt(1));
                presences.add(presence);
            }
        }
    }
}