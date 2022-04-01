package cg.applcation.systemedesurveillance.administrateur;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.UserAccount;


public class AddUserAccountFragment extends Fragment {


    EditText email, password;

    DatabaseAccess databaseAccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user_account, container, false);

        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.openForWritableDatabase();

        email = v.findViewById(R.id.teacher_email);
        password = v.findViewById(R.id.input_passwd);


        v.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(mEmail.matches("")){
                    Toast.makeText(getContext(), "Vous n'avez pas entré un email", Toast.LENGTH_LONG).show();
                }
                else if(mPassword.matches("")){
                    Toast.makeText(getContext(), "Vous n'avez pas entré un mot de passe", Toast.LENGTH_LONG).show();
                }
                else {
                    UserAccount userAccount = new UserAccount(mEmail, mPassword);
                    int id_teacher = databaseAccess.getIdFromTableTeacher(userAccount.getEmail());

                    boolean check = databaseAccess.addAccountUser(mEmail, userAccount.getPassword(), id_teacher);

                    if (!check) {
                        Toast.makeText(getContext(), "Failed adding Account in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(), "Added successfully :)", Toast.LENGTH_LONG).show();
                        email.setText("");
                        password.setText("");
                    }

                }
            }
        });

        return v;
    }
}