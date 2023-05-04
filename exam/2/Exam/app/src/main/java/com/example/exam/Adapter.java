package com.example.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    int resource;
    ArrayList<Student> students;
    DatabaseHandler db;
    public Adapter(Context context, int resource, ArrayList<Student> students) {
        this.context = context;
        this.resource = resource;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = students.get(position);
            holder.lbl_sid.setText(String.valueOf(student.getId()));
            holder.lbl_sname.setText(student.getName());
            holder.lbl_semail.setText(student.getEmail());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new DatabaseHandler(context);
                    db.deleteContact(student);
                    delete(holder.getAdapterPosition());

                }
            });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_sid,lbl_sname,lbl_semail;
        ImageButton edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lbl_sid = itemView.findViewById(R.id.lbl_sid);
            lbl_sname = itemView.findViewById(R.id.lbl_sname);
            lbl_semail = itemView.findViewById(R.id.lbl_semail);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }
    public void delete(int pos){
        students.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeRemoved(pos,students.size());
    }
}
