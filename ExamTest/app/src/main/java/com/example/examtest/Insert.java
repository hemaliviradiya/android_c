package com.example.examtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Insert extends AppCompatActivity implements View.OnClickListener {
    TextView t1;
    DataBaseHandler db;
    EditText addname;
    RadioGroup addgender;
    Spinner addStream;
    Button submit;
    public String Gender= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        t1 = findViewById(R.id.t1);
        addname=findViewById(R.id.addname);
        addgender=findViewById(R.id.addgender);
        addStream=findViewById(R.id.addstream);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(this);
        addgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.male == checkedId){
                    Gender = "Male";
                }
                else if(R.id.female == checkedId){
                    Gender = "Female";
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        //dept = spinnerDepartment.getSelectedItem().toString();
        db = new DataBaseHandler(this);
        Student s = new Student();
        s.setName(addname.getText().toString());
        s.setGender(Gender);
        s.setStream(addStream.getSelectedItem().toString());
        db.addStudent(s);
        startActivity(new Intent(getApplicationContext(),Home.class));



    }
}