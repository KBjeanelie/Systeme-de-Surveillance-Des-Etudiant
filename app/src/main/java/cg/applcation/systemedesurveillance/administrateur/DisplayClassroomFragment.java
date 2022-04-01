package cg.applcation.systemedesurveillance.administrateur;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.customadapter.CustomeClassroomAdapter;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Subject;


public class DisplayClassroomFragment extends Fragment {



    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    ArrayList<Classroom> classrooms;
    CustomeClassroomAdapter customeClassroomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display_subject, container, false);

        recyclerView = v.findViewById(R.id.recyclerview_display_classroom);
        databaseAccess = DatabaseAccess.getInstance(v.getContext());
        databaseAccess.openForReadableDatabase();

        classrooms = new ArrayList<Classroom>();

        displayData();
        customeClassroomAdapter = new CustomeClassroomAdapter(v.getContext(), classrooms);
        recyclerView.setAdapter(customeClassroomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }

    private void displayData(){
        Cursor cursor = databaseAccess.readAllDataInTableSubject();

        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "data stored :)", Toast.LENGTH_LONG).show();

            while (cursor.moveToNext()){
                Classroom classroom = new Classroom(cursor.getLong(0), cursor.getString(1));

                classrooms.add(classroom);
            }
        }
    }
}