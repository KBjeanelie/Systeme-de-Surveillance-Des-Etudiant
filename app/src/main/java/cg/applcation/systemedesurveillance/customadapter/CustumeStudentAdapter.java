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

public class CustumeStudentAdapter extends RecyclerView.Adapter<CustumeStudentAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<Student> students;

    public CustumeStudentAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public CustumeStudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.student_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustumeStudentAdapter.MyViewHolder holder, int position) {
        String f = students.get(position).getLastname() + " " + students.get(position).getFirstname();
        String e = students.get(position).getEmail();

        holder.fullName.setText(f);
        holder.email.setText(e);
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
        TextView fullName, email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.student_fullName_item);
            email = itemView.findViewById(R.id.student_email);
        }
    }
}
