package cg.applcation.systemedesurveillance.administrateur;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.customadapter.CustumeStudentAdapter;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Student;
import cg.applcation.systemedesurveillance.models.Teacher;


public class DisplayStudentFragment extends Fragment {

    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    ArrayList<Student> students;
    CustumeStudentAdapter custumeStudentAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display_student, container, false);

        recyclerView = v.findViewById(R.id.recyclerview_display_student);
        databaseAccess = DatabaseAccess.getInstance(getContext());
        students = new ArrayList<Student>();
        displayData();
        databaseAccess.closeDatabase();
        custumeStudentAdapter = new CustumeStudentAdapter(getContext(), students);

        recyclerView.setAdapter(custumeStudentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    public void displayData(){
        Cursor cursor = databaseAccess.readAllDataInTableStudent();

        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "data stored :)", Toast.LENGTH_LONG).show();

            while (cursor.moveToNext()){
                Student student = new Student(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                students.add(student);
            }
        }
    }
}