package com.example.examdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertData extends AppCompatActivity implements View.OnClickListener {

    EditText ename,eposition,esalary;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        ename = findViewById(R.id.empname);
        eposition = findViewById(R.id.empposition);
        esalary = findViewById(R.id.empsalary);

        add = findViewById(R.id.insert);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EmpDB db = new EmpDB(InsertData.this);
        db.addemp(ename.getText().toString().trim(),
                  eposition.getText().toString().trim(),
                  Integer.valueOf(esalary.getText().toString().trim()));
    }
}