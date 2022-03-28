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
import cg.applcation.systemedesurveillance.models.Teacher;

public class CustomeTeacherAdapter extends RecyclerView.Adapter<CustomeTeacherAdapter.MyViewHolder> {

    private  Context context;
    private  ArrayList<Teacher> teachers;

    public CustomeTeacherAdapter(Context context, ArrayList<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
    }

    @NonNull
    @Override
    public CustomeTeacherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.teacher_card_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomeTeacherAdapter.MyViewHolder holder, int position) {
        String fullName = teachers.get(position).getLastname() + " " + teachers.get(position).getFirstname();
        String status = teachers.get(position).getJob_function() + " | " + teachers.get(position).getWork_at();
        holder.fullName.setText(fullName);
        holder.status.setText(status);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullName, status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.teacher_fullname);
            status = itemView.findViewById(R.id.teacher_statuts);
        }
    }

}
