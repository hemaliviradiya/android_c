package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    RecyclerView recycleview;
    RecycleAdapter RA ;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recycleview = findViewById(R.id.recycleview);

        ArrayList<mobile> arrayList = db.show();
        RA = new RecycleAdapter(this,R.layout.row,arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recycleview.setLayoutManager(layoutManager);
        recycleview.addItemDecoration(new DividerItemDecoration(recycleview.getContext(),DividerItemDecoration.VERTICAL));
        recycleview.setAdapter(RA);
    }
}