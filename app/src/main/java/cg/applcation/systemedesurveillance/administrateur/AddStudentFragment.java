package cg.applcation.systemedesurveillance.administrateur;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Student;

public class AddStudentFragment extends Fragment {

    EditText lastname, firstname, email, tel, address, level, sex;
    Spinner spinner_classroom;
    String student_classroom;

    DatabaseAccess databaseAccess;
    ArrayList<String> classrooms = new ArrayList<String>();
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_student, container, false);
        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.openForReadableDatabase();

        lastname = v.findViewById(R.id.lastname);
        firstname = v.findViewById(R.id.firstname);
        email = v.findViewById(R.id.email);
        tel = v.findViewById(R.id.phone_number);
        address = v.findViewById(R.id.address);
        sex = v.findViewById(R.id.sex);
        spinner_classroom = v.findViewById(R.id.ads_id_classroom);

        classrooms = databaseAccess.getAllDataInTableClassroom();
        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_classroom, R.id.label_classroom, classrooms);
        spinner_classroom.setAdapter(adapter_class);

        spinner_classroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                student_classroom = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        v.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mLastname = lastname.getText().toString();
                String mFirstname = firstname.getText().toString();
                String mEmail = email.getText().toString();
                String mTel = tel.getText().toString();
                String mAddress = address.getText().toString();
                String mSex = sex.getText().toString();

                if (mLastname.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un nom", Toast.LENGTH_LONG).show();
                }else if (mFirstname.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un prenom", Toast.LENGTH_LONG).show();
                }else if (mEmail.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un email", Toast.LENGTH_LONG).show();
                }else if (mTel.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un nom", Toast.LENGTH_LONG).show();
                }else if (mAddress.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer une adresse", Toast.LENGTH_LONG).show();
                }else if (mSex.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un sex", Toast.LENGTH_LONG).show();
                }else
                {

                    Student student = new Student(mLastname, mFirstname, mEmail, mTel, mAddress, mSex);
                    int id_classroom = databaseAccess.getIdFromTableClassroom(student_classroom);
                    boolean result = databaseAccess.addStudent(student, id_classroom);
                    databaseAccess.closeDatabase();

                    if(!result){
                        Toast.makeText(getContext(), "Failed adding Student in database :(", Toast.LENGTH_LONG).show();
                    }else {
                       lastname.setText("");
                       firstname.setText("");
                       email.setText("");
                       address.setText("");
                       tel.setText("");
                       sex.setText("");
                       Toast.makeText(getContext(), "Added successfully :)", Toast.LENGTH_LONG).show();
                        /**getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ProfileFragment())
                                .commit();***/

                    }

                }
            }
        });
        return v;
    }
}