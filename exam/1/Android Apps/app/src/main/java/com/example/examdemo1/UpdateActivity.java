package com.example.examdemo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ename2,eposition2,esalary2;
    Button update,delete;

    String id,name,position,salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ename2 = findViewById(R.id.empname2);
        eposition2 = findViewById(R.id.empposition2);
        esalary2 = findViewById(R.id.empsalary2);

        update = findViewById(R.id.update);
        update.setOnClickListener(this);

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        getAndsetIntentData();

    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert For Deletion...");
        builder.setMessage("Are u sure u wnat to delete data(yes/no)");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EmpDB db = new EmpDB(UpdateActivity.this);
                db.delete(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    //Update button event:
    @Override
    public void onClick(View view) {
        EmpDB db = new EmpDB(UpdateActivity.this);
        name = ename2.getText().toString().trim();
        position = eposition2.getText().toString().trim();
        salary = esalary2.getText().toString().trim();
        db.update(id,name,position, Integer.parseInt(salary));
    }



    void getAndsetIntentData()
    {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("position") && getIntent().hasExtra("salary"))
        {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            position = getIntent().getStringExtra("position");
            salary = getIntent().getStringExtra("salary");

            ename2.setText(name);
            eposition2.setText(position);
            esalary2.setText(salary);
        }
        else
        {
            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
        }
    }
}