package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!SplashScreenActivity.current_session.isAuth()){
            SplashScreenActivity.current_session.logout();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }
}