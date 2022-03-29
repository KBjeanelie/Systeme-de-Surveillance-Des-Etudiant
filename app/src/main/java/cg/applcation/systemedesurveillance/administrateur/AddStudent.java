package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Student;

public class AddStudent extends AppCompatActivity {
    EditText lastname, firstname, email, tel, address, level, sex;
    Spinner spinner_classroom;
    ImageView ic_back;
    String student_classroom;

    DatabaseAccess databaseAccess;
    ArrayList<String> classrooms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spinner_classroom = findViewById(R.id.ads_id_classroom);

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();

        classrooms = databaseAccess.getAllDataInTableClassroom();
        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(this, R.layout.spinner_item_classroom, R.id.label_classroom, classrooms);
        spinner_classroom.setAdapter(adapter_class);

        this.lastname = findViewById(R.id.lastname);
        this.firstname = findViewById(R.id.firstname);
        this.email = findViewById(R.id.email);
        this.tel = findViewById(R.id.phone_number);
        this.address = findViewById(R.id.address);
        this.sex = findViewById(R.id.sex);

        spinner_classroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                student_classroom = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lastname = AddStudent.this.lastname.getText().toString();
                String firstname = AddStudent.this.firstname.getText().toString();
                String email = AddStudent.this.email.getText().toString();
                String tel = AddStudent.this.tel.getText().toString();
                String address = AddStudent.this.address.getText().toString();
                String sex = AddStudent.this.sex.getText().toString();

                if (lastname.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un nom", Toast.LENGTH_LONG).show();
                }else if (firstname.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un prenom", Toast.LENGTH_LONG).show();
                }else if (email.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un email", Toast.LENGTH_LONG).show();
                }else if (tel.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un nom", Toast.LENGTH_LONG).show();
                }else if (address.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer une adresse", Toast.LENGTH_LONG).show();
                }else if (sex.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un sex", Toast.LENGTH_LONG).show();
                }else
                {

                    Student student = new Student(lastname, firstname, email, tel, address, sex);
                    int id_classroom = databaseAccess.getIdFromTableClassroom(student_classroom);
                    boolean result = databaseAccess.addStudent(student, id_classroom);

                    if(!result){
                        Toast.makeText(getApplicationContext(), "Failed adding Student in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        AddStudent.this.lastname.setText("");
                        AddStudent.this.firstname.setText("");
                        AddStudent.this.email.setText("");
                        AddStudent.this.address.setText("");
                        AddStudent.this.tel.setText("");
                        AddStudent.this.sex.setText("");
                        Toast.makeText(getApplicationContext(), "Added successfully :)", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        databaseAccess.closeDatabase();

    }

    private boolean saveData(Student student){
        return false;
    }
}