package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Student;

public class ShowPresence extends AppCompatActivity {

    TextView classroom, subject, teacher_fullName, date_of_classes;
    String id_classes, id_teacher, id_subject, id_classroom;
    DatabaseAccess databaseAccess;
    ArrayList<Student> students;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_presence);
        SplashScreenActivity.current_session.checkSession(ShowPresence.this);


        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Liste de Presence");
        classroom = findViewById(R.id.classroom2_txt);
        subject = findViewById(R.id.subject2_txt);
        teacher_fullName = findViewById(R.id.teacher2_txt);
        date_of_classes = findViewById(R.id.date2_txt);

        getIntentData();

        String cl = databaseAccess.getSingleLabelFromClassroom(id_classroom);
        String sub = databaseAccess.getSingleLabelFromSubject(id_subject);
        String f = databaseAccess.getFullNameFromTeacher(id_teacher);


        classroom.setText(cl);
        subject.setText(sub);
        teacher_fullName.setText(f);

        findViewById(R.id.add_student_presence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowPresence.this, AddStudencePresence.class);
                intent.putExtra("id_classroom", id_classroom);
                startActivity(intent);
            }
        });

    }

    void getIntentData()
    {
        if (getIntent().hasExtra("id_classes") && getIntent().hasExtra("id_teacher") && getIntent().hasExtra("id_subject") &&
        getIntent().hasExtra("id_classroom") && getIntent().hasExtra("date_of_classes"))
        {
            id_classes = getIntent().getStringExtra("id_classes");
            id_teacher = getIntent().getStringExtra("id_teacher");
            id_subject = getIntent().getStringExtra("id_subject");
            id_classroom = getIntent().getStringExtra("id_classroom");
            String d = getIntent().getStringExtra("date_of_classes");
            date_of_classes.setText(d);
        }
    }


}