package cg.applcation.systemedesurveillance.administrateur;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.MainActivity;
import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.customadapter.CustomeTeacherAdapter;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Teacher;

public class DisplayTeacherFragment extends Fragment {


    DatabaseAccess databaseAccess;

    RecyclerView recyclerView;

    ArrayList<Teacher> teachers;

    CustomeTeacherAdapter customeTeacherAdapter;

    private Toolbar toolbar;

    public DisplayTeacherFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_display_teacher, container, false);

        recyclerView = v.findViewById(R.id.recyclerview_display_teacher);

        databaseAccess = DatabaseAccess.getInstance(v.getContext());
        databaseAccess.openForReadableDatabase();

        teachers = new ArrayList<Teacher>();
        displayData();
        databaseAccess.closeDatabase();

        customeTeacherAdapter = new CustomeTeacherAdapter(v.getContext(), teachers);
        recyclerView.setAdapter(customeTeacherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }

    public void displayData(){
        Cursor cursor = databaseAccess.readAllDataInTableTeacher();

        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "data stored :)", Toast.LENGTH_LONG).show();

            while (cursor.moveToNext()){
                Teacher teacher = new Teacher(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8),"");
                teacher.setId_teacher(Integer.parseInt(cursor.getString(0)));

                teachers.add(teacher);
            }
        }
    }
}