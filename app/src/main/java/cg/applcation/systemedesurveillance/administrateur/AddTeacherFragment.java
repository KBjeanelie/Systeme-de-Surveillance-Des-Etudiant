package cg.applcation.systemedesurveillance.administrateur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;


public class AddTeacherFragment extends Fragment {

    EditText lastname, firstname, email,job_function, work_at, tel, address, sex;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_teacher, container, false);

        this.lastname = v.findViewById(R.id.lastname);
        this.firstname = v.findViewById(R.id.firstname);
        this.email = v.findViewById(R.id.email);
        this.job_function = v.findViewById(R.id.job_function);
        this.work_at = v.findViewById(R.id.work_at);
        this.tel = v.findViewById(R.id.phone_number);
        this.address = v.findViewById(R.id.address);
        this.sex = v.findViewById(R.id.sex);

        v.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mLastname = lastname.getText().toString();
                String mFirstname = firstname.getText().toString();
                String mEmail = email.getText().toString();
                String mJob_function = job_function.getText().toString();
                String mWork_at = work_at.getText().toString();
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
                }else if (mJob_function.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer une Profession", Toast.LENGTH_LONG).show();
                }else if (mWork_at.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un lieux de travail", Toast.LENGTH_LONG).show();
                }else if (mTel.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un numero de telephone", Toast.LENGTH_LONG).show();
                }else if (mAddress.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer une adresse", Toast.LENGTH_LONG).show();
                }else if (mSex.matches(""))
                {
                    Toast.makeText(getContext(), "Vous n'avez pas entrer un sex", Toast.LENGTH_LONG).show();
                }else
                {
                    Teacher teacher = new Teacher(mLastname, mFirstname, mEmail, mJob_function, mWork_at, mTel, mAddress, mSex, "");
                    DatabaseAccess myDatabaseAccess = DatabaseAccess.getInstance(getContext());
                    myDatabaseAccess.openForWritableDatabase();
                    boolean check = myDatabaseAccess.addTeacher(teacher);

                    if (check){
                        Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                        lastname.setText("");
                        firstname.setText("");
                        email.setText("");
                        job_function.setText("");
                        work_at.setText("");
                        address.setText("");
                        tel.setText("");
                        sex.setText("");
                    }else {
                        Toast.makeText(getContext(), "Failed saving teacher in database :(", Toast.LENGTH_LONG).show();
                    }

                    myDatabaseAccess.closeDatabase();

                }
            }
        });

        return  v;
    }
}