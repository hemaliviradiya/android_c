package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText TxtName,Txtpwd;
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtName = findViewById(R.id.TxtName);
        Txtpwd = findViewById(R.id.Txtpwd);
        btnsubmit = findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);

        SharedPreferences sh = getSharedPreferences("info",MODE_PRIVATE);
        if(sh.contains("name") && sh.contains("pwd"))
        {
            finish();
            Intent intent =new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnsubmit)
        {
            SharedPreferences  sh = getSharedPreferences("info",MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("name",TxtName.getText().toString());
            editor.putString("pwd", Txtpwd.getText().toString());
            editor.commit();
            finish();
            Intent intent =new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
        }
    }
}