package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddSubject extends AppCompatActivity {

    EditText label_subject;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        databaseAccess = DatabaseAccess.getInstance(AddSubject.this);
        databaseAccess.openForWritableDatabase();

        label_subject = findViewById(R.id.add_subject_label);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = AddSubject.this.label_subject.getText().toString();

                if (label.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter le nom de la Matière", Toast.LENGTH_LONG).show();
                }else {

                    boolean check = databaseAccess.addNewSubject(label);
                    databaseAccess.closeDatabase();

                    if (!check){
                        Toast.makeText(AddSubject.this, "Failed adding Classroom in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        AddSubject.this.label_subject.setText("");
                        Toast.makeText(AddSubject.this, "Added successfully :)", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
