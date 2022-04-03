package cg.applcation.systemedesurveillance.enseignant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.customadapter.CustomesShowPresenceAdapter;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Presence;
import cg.applcation.systemedesurveillance.models.Student;
import cg.applcation.systemedesurveillance.models.Teacher;

public class ShowPresence extends AppCompatActivity {

    TextView classroom, subject, teacher_fullName, date_of_classes;

    String id_classes, id_teacher, id_subject, id_classroom, d_cl;

    DatabaseAccess databaseAccess;

    ArrayList<Student> students;

    ArrayList<Presence> presences;

    private Toolbar toolbar;

    RecyclerView recyclerView;

    CustomesShowPresenceAdapter customesShowPresenceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_presence);
        if (!SplashScreenActivity.current_session.isAuth()){
            SplashScreenActivity.current_session.logout();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Liste de Presence");
        toolbar.inflateMenu(R.menu.nav_option_menu);
        setSupportActionBar(toolbar);

        classroom = findViewById(R.id.classroom2_txt);
        subject = findViewById(R.id.subject2_txt);
        teacher_fullName = findViewById(R.id.teacher2_txt);
        date_of_classes = findViewById(R.id.date2_txt);
        recyclerView = findViewById(R.id.recyclerview_display_presence);


        getIntentData();

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();
        String cl = databaseAccess.getSingleLabelFromClassroom(id_classroom);
        String sub = databaseAccess.getSingleLabelFromSubject(id_subject);
        String f = databaseAccess.getFullNameFromTeacher(id_teacher);

        presences = new ArrayList<Presence>();
        storeDataInPresences();

        students =  new ArrayList<Student>();
        storeDataInStudents();

        databaseAccess.closeDatabase();

        if (students.isEmpty()){
            Toast.makeText(getApplicationContext(), "Aucune présence", Toast.LENGTH_LONG).show();
        }

        classroom.setText(cl);
        subject.setText(sub);
        teacher_fullName.setText(f);

        customesShowPresenceAdapter = new CustomesShowPresenceAdapter(ShowPresence.this, students, presences,
                Integer.parseInt(id_classroom), Integer.parseInt(id_classes));

        recyclerView.setAdapter(customesShowPresenceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        findViewById(R.id.add_student_presence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowPresence.this, AddStudencePresence.class);
                intent.putExtra("id_classroom", id_classroom);
                intent.putExtra("id_classes", id_classes);
                intent.putExtra("id_subject", id_subject);
                intent.putExtra("id_teacher", id_teacher);
                intent.putExtra("date_of_classes", d_cl);
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
            d_cl = getIntent().getStringExtra("date_of_classes");
            date_of_classes.setText(d_cl);
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

    public void storeDataInStudents(){
        Cursor cursor = databaseAccess.readAllDataInTableStudent();

        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                for (int i = 0; i < presences.size(); i++) {
                    if (presences.get(i).getId_student() == cursor.getInt(0) &&
                        (presences.get(i).getId_classes() == Integer.parseInt(id_classes)) &&
                            (cursor.getInt(7) == Integer.parseInt(id_classroom))
                        ){
                        Student student = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                        students.add(student);
                    }
                }
            }
        }else {
            Toast.makeText(getApplicationContext(), "Aucune présence", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}