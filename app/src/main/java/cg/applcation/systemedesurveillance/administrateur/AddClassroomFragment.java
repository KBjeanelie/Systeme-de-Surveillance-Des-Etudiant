package cg.applcation.systemedesurveillance.administrateur;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;


public class AddClassroomFragment extends Fragment {

    EditText label_classroom;
    DatabaseAccess databaseAccess;
    private Toolbar toolbar;

    public AddClassroomFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_classroom, container, false);

        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.openForWritableDatabase();

        label_classroom = v.findViewById(R.id.add_classroom_label);

        v.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = label_classroom.getText().toString();

                if (label.matches("")){
                    Toast.makeText(getContext(), "Enter le nom de la Classe", Toast.LENGTH_LONG).show();
                }else {

                    boolean check = databaseAccess.addNewClassroom(label);
                    databaseAccess.closeDatabase();

                    if (!check){
                        Toast.makeText(getContext(), "Failed adding Classroom in database :(", Toast.LENGTH_LONG).show();
                    }else {
                        label_classroom.setText("");
                        Toast.makeText(getContext(), "Added successfully :)", Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ProfileFragment())
                                .commit();
                    }
                }
            }
        });

        return  v;
    }
}