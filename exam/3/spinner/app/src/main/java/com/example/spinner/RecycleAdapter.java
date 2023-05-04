package com.example.spinner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    Context context;
    int resource;
    ArrayList<mobile> arrayList = new ArrayList<mobile>();

    public RecycleAdapter(Context context, int resource, ArrayList<mobile> arrayList) {
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        mobile mb = arrayList.get(position);
        holder.lblid.setText(String.valueOf(mb.getMid()));
        holder.lblname.setText(mb.getName());
        holder.lblcompany.setText(mb.getCompany());
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*DBHelper dbHelper = new DBHelper(context);
                mobile mb1 = new mobile();
                mb1.setMid(mb.getMid());
                mb1.setCompany("RAHUL");
                mb1.setName("MOIIIIZZZZZZZ");
                dbHelper.updatemobile(mb1);
                Toast.makeText(context, "update", Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(context,addMobile.class);
                intent.putExtra("mid",String.valueOf(mb.getMid()));
                intent.putExtra("mname",mb.getName());
                intent.putExtra("mcompany",mb.getCompany());
                context.startActivity(intent);
            }
        });
        holder.btndlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.dlt(String.valueOf(mb.getMid()));
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                delete(holder.getLayoutPosition());
            }
        });
    }

    public void delete(int postion)
    {
        arrayList.remove(postion);
        notifyItemRemoved(postion);
        notifyItemRangeChanged(postion,arrayList.size());
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblid,lblname,lblcompany;
        Button btnupdate,btndlt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblid = itemView.findViewById(R.id.lblid);
            lblname = itemView.findViewById(R.id.lblname);
            lblcompany = itemView.findViewById(R.id.lblcompany);
            btndlt  = itemView.findViewById(R.id.btndlt);
            btnupdate = itemView.findViewById(R.id.btnupdate);
        }
    }
}
