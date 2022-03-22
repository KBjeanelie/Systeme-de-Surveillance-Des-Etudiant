package cg.applcation.systemedesurveillance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cg.applcation.systemedesurveillance.administrateur.AddStudent;
import cg.applcation.systemedesurveillance.administrateur.AddTeacher;
import cg.applcation.systemedesurveillance.administrateur.AddUserAccount;
import cg.applcation.systemedesurveillance.administrateur.DisplayTeachers;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.card_add_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddStudent.class));
                finish();
            }
        });

        findViewById(R.id.card_add_teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTeacher.class));
                finish();
            }
        });

        findViewById(R.id.card_add_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddUserAccount.class));
                finish();
            }
        });

        findViewById(R.id.card_display_teachers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayTeachers.class));
                finish();
            }
        });

        findViewById(R.id.ic_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}