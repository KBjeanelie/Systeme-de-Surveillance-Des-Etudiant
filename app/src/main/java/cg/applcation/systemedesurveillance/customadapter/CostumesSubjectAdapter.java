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
import cg.applcation.systemedesurveillance.models.Subject;

public class CostumesSubjectAdapter extends RecyclerView.Adapter<CostumesSubjectAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<Subject> subjects;

    public CostumesSubjectAdapter(Context context, ArrayList<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public CostumesSubjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.label_subject_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CostumesSubjectAdapter.MyViewHolder holder, int position) {
        holder.label_subject.setText(subjects.get(position).getLabel());
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView label_subject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            label_subject = itemView.findViewById(R.id.label_subject_txt);
        }
    }
}
