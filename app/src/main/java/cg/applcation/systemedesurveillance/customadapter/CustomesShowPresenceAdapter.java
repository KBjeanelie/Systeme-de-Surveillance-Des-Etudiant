package cg.applcation.systemedesurveillance.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.Presence;
import cg.applcation.systemedesurveillance.models.Student;

public class CustomesShowPresenceAdapter extends RecyclerView.Adapter<CustomesShowPresenceAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Student> students;
    ArrayList<Presence> presences;
    int id_classroom;
    int id_classes;

    public CustomesShowPresenceAdapter(Context context, ArrayList<Student> students, ArrayList<Presence> presence, int id_classroom, int id_classes) {
        this.context = context;
        this.students = students;
        presences = presence;
        this.id_classroom = id_classroom;
        this.id_classes = id_classes;
    }

    @NonNull
    @Override
    public CustomesShowPresenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.student_attendance_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        for (int i = 0; i < presences.size(); i++) {
            if ((presences.get(i).getId_classes() == id_classes) && (presences.get(i).getId_student() == students.get(position).getId_student())
            &&  students.get(position).getId_classroom() == id_classroom){
                String f = students.get(position).getLastname() + " " + students.get(position).getFirstname();
                holder.student_fullName.setText(f);
            }
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_fullName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_fullName = itemView.findViewById(R.id.student_fullname);
        }
    }
}
