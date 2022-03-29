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
import cg.applcation.systemedesurveillance.models.Classes;
import cg.applcation.systemedesurveillance.models.Classroom;
import cg.applcation.systemedesurveillance.models.Subject;

public class CustomesClassesAdapter extends RecyclerView.Adapter<CustomesClassesAdapter.MyViewHolder> {

    private final Context context;
    ArrayList<Classes> classes;
    ArrayList<Classroom> classrooms;
    ArrayList<Subject> subjects;

    public CustomesClassesAdapter(Context context, ArrayList<Classes> classes, ArrayList<Classroom> classrooms, ArrayList<Subject> subjects) {
        this.context = context;
        this.classes = classes;
        this.classrooms = classrooms;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public CustomesClassesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.class_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomesClassesAdapter.MyViewHolder holder, int position) {
        int id_classroom = classes.get(position).getId_classroom();
        int id_subject = classes.get(position).getId_subject();
        holder.date_of_class.setText(classes.get(position).getDate_of_classes());
        holder.label_subject.setText(String.valueOf(id_subject));
        holder.label_classroom.setText(String.valueOf(id_classroom));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return classes.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView date_of_class, label_classroom, label_subject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date_of_class = itemView.findViewById(R.id.class2_txt);
            label_classroom = itemView.findViewById(R.id.class_s2_txt);
            label_subject = itemView.findViewById(R.id.class_cl2_txt);
        }
    }
}
