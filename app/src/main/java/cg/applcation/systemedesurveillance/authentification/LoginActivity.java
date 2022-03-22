package cg.applcation.systemedesurveillance.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.enseignant.TeacherDashboard;
import cg.applcation.systemedesurveillance.models.UserAccount;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    UserAccount userAccount = new UserAccount(username, "", password);

                    if (LoginActivity.this.check(userAccount) == 1)
                    {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }else if (LoginActivity.this.check(userAccount) == 2)
                    {
                        startActivity(new Intent(getApplicationContext(), TeacherDashboard.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "identiant invalid :(", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private int check(UserAccount userAccount){
        if (userAccount.getEmail().equals(SplashScreenActivity.ADMIN_USERNAME)
                && userAccount.getPassword().equals(SplashScreenActivity.ADMIN_PASSWORD)){
            return 1;
        }else  if (userAccount.getEmail().equals(SplashScreenActivity.USERNAME)
                && userAccount.getPassword().equals(SplashScreenActivity.PASSWORD)){
            return 2;
        }else{
            return 0;
        }

    }
}