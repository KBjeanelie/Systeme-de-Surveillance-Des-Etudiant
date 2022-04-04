package cg.applcation.systemedesurveillance.administrateur;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.customadapter.CustomesClassesAdapter;
import cg.applcation.systemedesurveillance.enseignant.TeacherDashboard;
import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Subject;

public class MainFragment extends Fragment {



    ArrayList<Classes> classes;

    ArrayList<Classroom> classrooms;

    ArrayList<Subject> subjects;

    DatabaseAccess databaseAccess;

    RecyclerView recyclerView;
    private CustomesClassesAdapter customesClassesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        databaseAccess = DatabaseAccess.getInstance(v.getContext());
        databaseAccess.openForReadableDatabase();

        recyclerView = v.findViewById(R.id.recyclerview_admin_display_classes);

        classes = new ArrayList<Classes>();
        storeDataInClasses();

        classrooms = new ArrayList<Classroom>();
        getClassroom();

        subjects = new ArrayList<Subject>();
        getSubject();

        customesClassesAdapter = new CustomesClassesAdapter(v.getContext(), classes, classrooms, subjects);

        recyclerView.setAdapter(customesClassesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }

    public void storeDataInClasses(){
        Cursor cursor = databaseAccess.readAllDataInTableClasses();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Classes single_classes = new Classes(cursor.getInt(0), cursor.getInt(1),
                        cursor.getInt(2), cursor.getInt(3), cursor.getString(4));

                classes.add(single_classes);
            }
        }
    }

    public void getSubject(){
        Cursor cursor = databaseAccess.readAllDataInTableSubject();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Subject subject = new Subject(cursor.getString(1), cursor.getLong(0));
                subjects.add(subject);
            }
        }
    }

    public void getClassroom(){
        Cursor cursor = databaseAccess.readAllDataInTableClassroom();
        if ( cursor!= null && cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Classroom classroom = new Classroom(cursor.getLong(0), cursor.getString(1));
                classrooms.add(classroom);
            }
        }
    }
}