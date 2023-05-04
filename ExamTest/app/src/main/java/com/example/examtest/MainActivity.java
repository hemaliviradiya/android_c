package com.example.examtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText no,pwd;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t1);
        no=findViewById(R.id.no);
        pwd=findViewById(R.id.pwd);
        SharedPreferences spf2 = getSharedPreferences("login",MODE_PRIVATE);
        if(spf2.contains("no")&&spf2.contains("pwd")){
            startActivity(new Intent(getApplicationContext(),Home.class));
        }else{
            login=findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences spf = getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("no",no.getText().toString());
                    editor.putString("pwd",pwd.getText().toString());
                    editor.commit();
                    no.setText("");
                    pwd.setText("");
                    finish();
                    startActivity(new Intent(getApplicationContext(),Home.class));

                }
            });
        }
    }
}