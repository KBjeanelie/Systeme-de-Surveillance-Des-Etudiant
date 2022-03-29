package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.UserAccount;

public class AddUserAccount extends AppCompatActivity {

    EditText email, teacher_tel, password;
    ImageView ic_back;
    TextView app_bar_title;

    DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_account);

        databaseAccess = DatabaseAccess.getInstance(AddUserAccount.this);
        databaseAccess.openForWritableDatabase();

        email = findViewById(R.id.teacher_email);
        teacher_tel = findViewById(R.id.phone_number);
        password = findViewById(R.id.input_passwd);


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = AddUserAccount.this.email.getText().toString().trim();
                String tel = AddUserAccount.this.teacher_tel.getText().toString().trim();
                String password = AddUserAccount.this.password.getText().toString().trim();

                if(email.matches("")){
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entré un email", Toast.LENGTH_LONG).show();
                }
                else if(tel.matches("")){
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entré un numero de telephone", Toast.LENGTH_LONG).show();
                }
                else if(password.matches("")){
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas entré un mot de passe", Toast.LENGTH_LONG).show();
                }
                else {
                    UserAccount userAccount = new UserAccount(email,password);
                    int id_teacher = databaseAccess.getIdFromTableTeacher(userAccount.getEmail());

                    boolean check = databaseAccess.addAccountUser(email, userAccount.getPassword(), id_teacher);

                    if (!check) {
                        Toast.makeText(getApplicationContext(), "Failed adding Account in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Added successfully :)", Toast.LENGTH_LONG).show();
                        AddUserAccount.this.email.setText("");
                        AddUserAccount.this.teacher_tel.setText("");
                        AddUserAccount.this.password.setText("");
                    }

                }
            }
        });
    }
}