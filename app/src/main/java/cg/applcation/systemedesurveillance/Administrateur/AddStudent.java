package cg.applcation.systemedesurveillance.Administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cg.applcation.systemedesurveillance.Authentification.LoginActivity;
import cg.applcation.systemedesurveillance.R;

public class AddStudent extends AppCompatActivity {

    ImageView ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        ic_back = findViewById(R.id.icon_back);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudent.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}