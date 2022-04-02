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
import cg.applcation.systemedesurveillance.customadapter.CustomeSubjectAdapter;
import cg.applcation.systemedesurveillance.models.DatabaseAccess;
import cg.applcation.systemedesurveillance.models.Subject;


public class DisplaySubjectFragment extends Fragment {

    DatabaseAccess databaseAccess;
    RecyclerView recyclerView;
    ArrayList<Subject> subjects;
    CustomeSubjectAdapter customeSubjectAdapter;

    public DisplaySubjectFragment() {
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
        View v = inflater.inflate(R.layout.fragment_display_subject, container, false);

        databaseAccess = DatabaseAccess.getInstance(v.getContext());
        databaseAccess.openForReadableDatabase();

        recyclerView = v.findViewById(R.id.recyclerview_display_subject);

        subjects = new ArrayList<Subject>();

        displayData();

        customeSubjectAdapter = new CustomeSubjectAdapter(v.getContext(), subjects);
        recyclerView.setAdapter(customeSubjectAdapter);
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
                Subject subject = new Subject(cursor.getString(1), cursor.getLong(0));
                subjects.add(subject);
            }
        }
    }
}