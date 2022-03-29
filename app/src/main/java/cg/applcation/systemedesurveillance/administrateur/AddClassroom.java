package cg.applcation.systemedesurveillance.administrateur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;

public class AddClassroom extends AppCompatActivity {

    EditText label_classroom;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);
        databaseAccess = DatabaseAccess.getInstance(AddClassroom.this);
        databaseAccess.openForWritableDatabase();

        label_classroom = findViewById(R.id.add_classroom_label);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = AddClassroom.this.label_classroom.getText().toString();

                if (label.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter le nom de la Classe", Toast.LENGTH_LONG).show();
                }else {

                    boolean check = databaseAccess.addNewClassroom(label);
                    databaseAccess.closeDatabase();

                    if (!check){
                        Toast.makeText(AddClassroom.this, "Failed adding Classroom in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        AddClassroom.this.label_classroom.setText("");
                        Toast.makeText(AddClassroom.this, "Added successfully :)", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
