package com.example.examdemo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.MyViewholder> {
    private Context context;
    private ArrayList eid,ename,eposition,esalary;
    Activity activity;

    public EmpAdapter(Activity activity,Context context, ArrayList eid, ArrayList ename, ArrayList eposition, ArrayList esalary) {
        this.activity = activity;
        this.context = context;
        this.eid = eid;
        this.ename = ename;
        this.eposition = eposition;
        this.esalary = esalary;
    }

    @NonNull
    @Override
    public EmpAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpAdapter.MyViewholder holder,int position) {

        holder.empid.setText(String.valueOf(eid.get(position)));
        holder.empname.setText(String.valueOf(ename.get(position)));
        holder.empposition.setText(String.valueOf(eposition.get(position)));
        holder.empsalary.setText(String.valueOf(esalary.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,UpdateActivity.class);
                i.putExtra("id",String.valueOf(eid.get(position)));
                i.putExtra("name",String.valueOf(ename.get(position)));
                i.putExtra("position",String.valueOf(eposition.get(position)));
                i.putExtra("salary",String.valueOf(esalary.get(position)));
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eid.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
    TextView empid,empname,empsalary,empposition;
    LinearLayout mainLayout;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            empid =itemView.findViewById(R.id.eid);
            empname =itemView.findViewById(R.id.ename);
            empposition =itemView.findViewById(R.id.eposition);
            empsalary =itemView.findViewById(R.id.esalary);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
