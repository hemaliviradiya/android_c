package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Insert extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler db;
    EditText sname,semail;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        sname = findViewById(R.id.edt_sname);
        semail = findViewById(R.id.edt_semail);
        add = findViewById(R.id.btn_add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        db = new DatabaseHandler(this);
        Student s = new Student();
        s.setName(sname.getText().toString());
        s.setEmail(semail.getText().toString());
        db.AddStudent(s);

    }
}