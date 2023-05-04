package com.example.examtest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    Context context;
    int Resource;
    ArrayList<Student> students;

    DataBaseHandler db;
    public StudentAdapter(Context context, int resource, ArrayList<Student> students) {
        this.context = context;
        Resource = resource;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Resource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.sid.setText(String.valueOf(student.getRollno()));
        holder.sname.setText(student.getName());
        holder.sgender.setText(student.getGender());
        holder.sstream.setText(student.getStream());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you Sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db = new DataBaseHandler(context);
                                db.deleteContact(student);
                                delete(holder.getAdapterPosition());

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sid,sname,sgender,sstream;
        ImageButton edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sid = itemView.findViewById(R.id.sid);
            sname = itemView.findViewById(R.id.sname);
            sgender = itemView.findViewById(R.id.sgender);
            sstream = itemView.findViewById(R.id.sstream);
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
