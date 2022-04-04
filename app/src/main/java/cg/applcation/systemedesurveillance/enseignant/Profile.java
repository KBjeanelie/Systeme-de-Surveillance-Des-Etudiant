package cg.applcation.systemedesurveillance.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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


        TextView fullname, email, phone, sex, job, work, address;

        fullname = findViewById(R.id.profile_fullName);
        email = findViewById(R.id.profile_mail);
        phone = findViewById(R.id.profile_phone);
        sex = findViewById(R.id.profile_sex);
        job = findViewById(R.id.profile_job);
        work = findViewById(R.id.profile_work);
        address = findViewById(R.id.profile_address);

        String f = SplashScreenActivity.current_session.getCurrent_teacher().getLastname() + " " + SplashScreenActivity.current_session.getCurrent_teacher().getFirstname();
        fullname.setText(f);
        email.setText(SplashScreenActivity.current_session.getCurrent_teacher().getEmail());
        phone.setText(SplashScreenActivity.current_session.getCurrent_teacher().getTel());
        sex.setText(SplashScreenActivity.current_session.getCurrent_teacher().getSex());
        job.setText(SplashScreenActivity.current_session.getCurrent_teacher().getJob_function());
        work.setText(SplashScreenActivity.current_session.getCurrent_teacher().getWork_at());
        address.setText(SplashScreenActivity.current_session.getCurrent_teacher().getAddress());
    }
}