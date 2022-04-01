package cg.applcation.systemedesurveillance.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cg.applcation.systemedesurveillance.R;
import cg.applcation.systemedesurveillance.models.Classroom;

public class CustomeClassroomAdapter extends RecyclerView.Adapter<CustomeClassroomAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Classroom> classrooms;

    public CustomeClassroomAdapter(Context context, ArrayList<Classroom> classrooms) {
        this.context = context;
        this.classrooms = classrooms;
    }

    @NonNull
    @Override
    public CustomeClassroomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.label_classroom_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomeClassroomAdapter.MyViewHolder holder, int position) {
        holder.label_classroom.setText(classrooms.get(position).getLabel_classroom());
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView label_classroom;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            label_classroom = itemView.findViewById(R.id.label_classroom_txt);
        }
    }
}
