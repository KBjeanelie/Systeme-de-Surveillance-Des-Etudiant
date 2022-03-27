package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;


public class AddTeacher extends AppCompatActivity {
    EditText lastname, firstname, email,job_function, work_at, tel, address, sex;
    ImageView ic_back;
    TextView app_bar_title;

    DatabaseAccess myDatabaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        app_bar_title = findViewById(R.id.app_bar_title);
        app_bar_title.setText("Ajouter un enseignant");

        ic_back = findViewById(R.id.icon_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTeacher.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        this.lastname = findViewById(R.id.lastname);
        this.firstname = findViewById(R.id.firstname);
        this.email = findViewById(R.id.email);
        this.job_function = findViewById(R.id.job_function);
        this.work_at = findViewById(R.id.work_at);
        this.tel = findViewById(R.id.phone_number);
        this.address = findViewById(R.id.address);
        this.sex = findViewById(R.id.sex);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lastname = AddTeacher.this.lastname.getText().toString();
                String firstname = AddTeacher.this.firstname.getText().toString();
                String email = AddTeacher.this.email.getText().toString();
                String job_function = AddTeacher.this.job_function.getText().toString();
                String work_at = AddTeacher.this.work_at.getText().toString();
                String tel = AddTeacher.this.tel.getText().toString();
                String address = AddTeacher.this.address.getText().toString();
                String sex = AddTeacher.this.sex.getText().toString();

                if (lastname.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un nom", Toast.LENGTH_LONG).show();
                }else if (firstname.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un prenom", Toast.LENGTH_LONG).show();
                }else if (email.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un email", Toast.LENGTH_LONG).show();
                }else if (job_function.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer une Profession", Toast.LENGTH_LONG).show();
                }else if (work_at.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un lieux de travail", Toast.LENGTH_LONG).show();
                }else if (tel.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un numero de telephone", Toast.LENGTH_LONG).show();
                }else if (address.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer une adresse", Toast.LENGTH_LONG).show();
                }else if (sex.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entrer un sex", Toast.LENGTH_LONG).show();
                }else
                {
                    Teacher teacher = new Teacher(lastname, firstname, email, job_function, work_at, tel, address,  sex, "");
                    myDatabaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    boolean check = myDatabaseAccess.addTeacher(teacher);

                    if (check){
                        Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                        AddTeacher.this.lastname.setText("");
                        AddTeacher.this.firstname.setText("");
                        AddTeacher.this.email.setText("");
                        AddTeacher.this.job_function.setText("");
                        AddTeacher.this.work_at.setText("");
                        AddTeacher.this.address.setText("");
                        AddTeacher.this.tel.setText("");
                        AddTeacher.this.sex.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(), "Failed saving teacher in database :(", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean saveData(Teacher teacher){
        return false;
    }
}
