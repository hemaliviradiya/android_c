package com.example.examtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener {

    Button add;
    RecyclerView rv;
    StudentAdapter adapter;
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add=findViewById(R.id.add);
        rv=findViewById(R.id.rv);
        add.setOnClickListener(this);
        db = new DataBaseHandler(this);
        ArrayList<Student> students = db.getAll();

        adapter = new StudentAdapter(this,R.layout.row,students);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        //rv.addItemDecoration(new DividerItemDecoration(t));
        rv.setAdapter(adapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logout1){

            SharedPreferences spf2 = getSharedPreferences("login",MODE_PRIVATE);
            if(spf2.contains("no")&&spf2.contains("pwd")){
                spf2.edit().clear().commit();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),Insert.class));
    }
}
