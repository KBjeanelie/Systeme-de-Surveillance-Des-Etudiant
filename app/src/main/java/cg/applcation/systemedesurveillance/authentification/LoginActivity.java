package cg.applcation.systemedesurveillance.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.enseignant.TeacherDashboard;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;
import cg.applcation.systemedesurveillance.models.UserAccount;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    DatabaseAccess databaseAccess;

    ArrayList<Teacher> teachers;
    ArrayList<UserAccount> userAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.openForReadableDatabase();;

        teachers = getAllTeacher();
        userAccounts = getAllAccount();

        username = findViewById(R.id.input_username_login);
        password = findViewById(R.id.input_passwd_login);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = LoginActivity.this.username.getText().toString().trim();
                String password = LoginActivity.this.password.getText().toString().trim();

                if (username.matches("")){
                    Toast.makeText(getApplicationContext(), "email ou tel invalid", Toast.LENGTH_LONG).show();
                }else if (password.matches("")){
                    Toast.makeText(getApplicationContext(), "mot de passe invalid", Toast.LENGTH_LONG).show();
                }else{

                    if (username.equals(SplashScreenActivity.ADMIN_USERNAME) && password.equals(SplashScreenActivity.ADMIN_PASSWORD)){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else {
                        for (UserAccount user: userAccounts) {
                            if (user.getEmail().equals(username) && user.getPassword().equals(password)){

                                for (Teacher t: teachers) {
                                    if (t.getId_teacher() == user.getId_teacher()){
                                        SplashScreenActivity.current_session = new Session(true, t, user);
                                        startActivity(new Intent(LoginActivity.this, TeacherDashboard.class));
                                        finish();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public ArrayList<Teacher> getAllTeacher(){
        Cursor cursor = databaseAccess.readAllDataInTableTeacher();
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(), "No data found :(", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                Teacher teacher = new Teacher(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8),"");
                teacher.setId_teacher(Integer.parseInt(cursor.getString(0)));

                teachers.add(teacher);
            }
        }
        return teachers;
    }

    public ArrayList<UserAccount> getAllAccount(){
        Cursor cursor = databaseAccess.readAllDataInTableAccount();
        ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(), "No data found :(", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                UserAccount userAccount = new UserAccount(cursor.getString(0), cursor.getString(1));
                userAccount.setId_teacher(cursor.getInt(2));
                accounts.add(userAccount);
            }
        }
        return accounts;
    }

}