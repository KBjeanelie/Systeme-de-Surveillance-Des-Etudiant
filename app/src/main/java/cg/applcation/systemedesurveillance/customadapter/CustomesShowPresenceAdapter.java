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
import cg.applcation.systemedesurveillance.models.Student;

public class CustomesShowPresenceAdapter extends RecyclerView.Adapter<CustomesShowPresenceAdapter.MyViewHolder> {

    private Context context;


    @NonNull
    @Override
    public CustomesShowPresenceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.student_attendance_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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
        TextView student_fullName, presence_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_fullName = itemView.findViewById(R.id.student_fullname);
            presence_date = itemView.findViewById(R.id.presence_date_txt);
        }
    }
}
