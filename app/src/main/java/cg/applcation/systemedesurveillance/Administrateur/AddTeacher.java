package cg.applcation.systemedesurveillance.Administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cg.applcation.systemedesurveillance.Authentification.LoginActivity;
import cg.applcation.systemedesurveillance.R;

public class AddTeacher extends AppCompatActivity {

    ImageView ic_back;
    TextView app_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        ic_back = findViewById(R.id.icon_back);
        app_bar_title = findViewById(R.id.app_bar_title);

        app_bar_title.setText("Ajouter un Enseignant");

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTeacher.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
