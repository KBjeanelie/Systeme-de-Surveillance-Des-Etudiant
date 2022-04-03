package cg.applcation.systemedesurveillance.enseignant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.SplashScreenActivity;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;

public class AboutApp extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        if (!SplashScreenActivity.current_session.isAuth()){
            SplashScreenActivity.current_session.logout();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("Dashboard");
        toolbar.inflateMenu(R.menu.nav_option_menu);
        setSupportActionBar(toolbar);
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