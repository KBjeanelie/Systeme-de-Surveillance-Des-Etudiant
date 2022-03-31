package cg.applcation.systemedesurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.authentification.Session;
import cg.applcation.systemedesurveillance.enseignant.AddStudencePresence;
import cg.applcation.systemedesurveillance.enseignant.ShowPresence;
import cg.applcation.systemedesurveillance.enseignant.TeacherDashboard;
import cg.applcation.systemedesurveillance.models.Teacher;
import cg.applcation.systemedesurveillance.models.UserAccount;

public class SplashScreenActivity extends AppCompatActivity {

    /**  THIS IS THE DEFAULT ADMIN INFORMATION FOR LOG INTO APP  */
    public static String ADMIN_USERNAME = "admin";
    public static String ADMIN_PASSWORD = "admin";

    public static Session current_session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);

                finish();

            }

        }, 3*1000); // wait for 5 seconds
    }

}