package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;

public class DisplayTeachers extends AppCompatActivity {

    ImageView ic_back;
    TextView app_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_teachers);

        ic_back = findViewById(R.id.icon_back);
        app_bar_title = findViewById(R.id.app_bar_title);

        app_bar_title.setText("Lister les enseignant");

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayTeachers.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}