package com.example.examdemo1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmpAdapter adapter;
    EmpDB db;
    ArrayList<String> eid,ename,eposition,esalary;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv1);
        db = new EmpDB(MainActivity.this);
        eid = new ArrayList<>();
        ename = new ArrayList<>();
        eposition = new ArrayList<>();
        esalary = new ArrayList<>();

        stroearrays();
        adapter= new EmpAdapter(MainActivity.this,this,eid,ename,eposition,esalary);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            recreate();
        }
    }


    void stroearrays()
    {
        Cursor cursor = db.empdisp();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Not Display...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                eid.add(cursor.getString(0));
                ename.add(cursor.getString(1));
                eposition.add(cursor.getString(2));
                esalary.add(cursor.getString(3));
            }
        }
    }
//For Menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

     @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.addmenu:
                Intent i = new Intent(this,InsertData.class);
                startActivity(i);
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}